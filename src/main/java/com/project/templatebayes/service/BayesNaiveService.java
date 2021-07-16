package com.project.templatebayes.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.comparator.Comparators;

import com.project.templatebayes.bean.Attribute;
import com.project.templatebayes.bean.MapAttribute;
import com.project.templatebayes.bean.Request;
import com.project.templatebayes.bean.Result;
import com.project.templatebayes.model.DataTrainingView;
import com.project.templatebayes.model.MstParameter;
import com.project.templatebayes.model.MstType;

@Service
public class BayesNaiveService {

	@Autowired
	private DataTrainingService dataTrainingService;

	@Autowired
	private MstTypeService mstTypeService;

	public List<Result> run(Integer typeId, List<Request> requests) {
		// prepare data training
		List<DataTrainingView> dataTrainingViews = dataTrainingService.getAllDataByTypeView(typeId);

		if (dataTrainingViews.isEmpty()) {
			return null;
		}

		// set value attribute of params
		List<Attribute> attrParams = getValueAttrParams(typeId, dataTrainingViews);
		// set value attribute of result
		Attribute attrResult = getValueAttrResult(typeId, dataTrainingViews);
		// calculate count, prob from value attribute of result
		List<MapAttribute> mapAttrResults = countProbResult(attrResult, dataTrainingViews);
		// calculate count, prob from value attribute of param
		List<MapAttribute> mapAttrParams = countProbParams(attrParams, mapAttrResults, dataTrainingViews);
		// run prediction
		List<Result> predictionResults = prediction(requests, mapAttrParams, mapAttrResults);

		return predictionResults;
	}

	private List<Attribute> getValueAttrParams(Integer typeId, List<DataTrainingView> dataTrainingViews) {
		List<Attribute> attrParams = new ArrayList<Attribute>();
		MstType type = mstTypeService.getDataById(typeId);
		List<MstParameter> params = new ArrayList<MstParameter>();

		type.getMstMapTypeParameters().stream().forEach(p -> {
			params.add(p.getMstParameter());
		});

		params.stream().forEach(p -> {
			List<String> valueAttrs = new ArrayList<>();
			p.getMstMapTypeParameters().stream().forEach(mtp -> {
				dataTrainingViews.stream().filter(f -> f.getParameterSeq() == mtp.getParameterSeq()).map(m -> {
					return m.getParameterValue();
				}).distinct().forEach(d -> {
					valueAttrs.add(d);
				});
				;
			});

			Attribute attrParam = new Attribute(p.getName(), valueAttrs);
			attrParams.add(attrParam);
		});

//		attrParams.stream().forEach(p -> {
//			System.out.println(p);
//		});

		return attrParams;
	}

	private Attribute getValueAttrResult(Integer typeId, List<DataTrainingView> dataTrainingViews) {
		List<String> valueAttrs = new ArrayList<>();
		dataTrainingViews.stream().map(m -> {
			return m.getResult();
		}).distinct().forEach(d -> {
			valueAttrs.add(d);
		});
		Attribute attrResult = new Attribute("result", valueAttrs);

//		System.out.println(attrResult);

		return attrResult;
	}

	private List<MapAttribute> countProbResult(Attribute attrResult, List<DataTrainingView> dataTrainingViews) {
		List<MapAttribute> mapAttrResults = new ArrayList<MapAttribute>();

		int sumCount = (int) dataTrainingViews.stream().map(m -> m.getDataTrainingId()).distinct().count();

		attrResult.getValues().forEach(p -> {
			int count = (int) dataTrainingViews.stream().filter(dtv -> dtv.getResult().equalsIgnoreCase(p)).map(m -> {
				return m.getDataTrainingId() + "-" + m.getResult();
			}).distinct().count();
			double prob = (double) count / sumCount;
			MapAttribute mapAttrResult = new MapAttribute(attrResult.getName(), p, p, count, prob);
			mapAttrResults.add(mapAttrResult);
		});

//		mapAttrResults.stream().forEach(p -> {
//			System.out.println(p);
//		});

		return mapAttrResults;
	}

	private List<MapAttribute> countProbParams(List<Attribute> attrParams, List<MapAttribute> mapAttrResults,
			List<DataTrainingView> dataTrainingViews) {

		List<MapAttribute> mapAttrParams = new ArrayList<MapAttribute>();

		attrParams.stream().forEach(p -> {
			mapAttrResults.stream().forEach(r -> {
				int countAttrResult = r.getCount();
				p.getValues().stream().forEach(s -> {
					int countAttrParam = (int) dataTrainingViews.stream()
							.filter(f1 -> f1.getResult().equalsIgnoreCase(r.getValueAttribute()))
							.filter(f2 -> f2.getParameterName().equalsIgnoreCase(p.getName()))
							.filter(f3 -> f3.getParameterValue().equalsIgnoreCase(s)).count();

					double prob = (double) countAttrParam / countAttrResult;
					MapAttribute mapAttrParam = new MapAttribute(p.getName(), s, r.getResult(), countAttrParam, prob);
					mapAttrParams.add(mapAttrParam);
				});
			});
		});

//		mapAttrParams.stream().forEach(p -> {
//			System.out.println(p);
//		});

		return mapAttrParams;
	}

	private List<Result> prediction(List<Request> requests, List<MapAttribute> mapAttrParams, List<MapAttribute> mapAttrResults) {

		List<Result> results = new ArrayList<Result>();

		int sumCountResult = mapAttrResults.stream().mapToInt(MapAttribute::getCount).sum();
		mapAttrResults.stream().forEach(result -> {
			AtomicReference<Double> probResult = new AtomicReference<>();
			probResult.set(result.getProb());
			mapAttrParams.stream().filter(f1 -> f1.getResult().equalsIgnoreCase(result.getResult())).forEach(mapAttrParam -> {
//				System.out.println("masuk mapAttrParam");
				requests.stream().filter(f2 -> f2.getNameAttribute().equalsIgnoreCase(mapAttrParam.getNameAttribute()))
						.filter(f3 -> f3.getValueAttribute().equalsIgnoreCase(mapAttrParam.getValueAttribute())).forEach(request -> {
							System.out.println("masuk request");
							boolean checkZero = requests.stream().filter(f4 -> {
								return mapAttrParams.stream().filter(f5 -> {
									return f4.getNameAttribute().equalsIgnoreCase(f5.getNameAttribute())
											&& f4.getValueAttribute().equalsIgnoreCase(f5.getValueAttribute()) && f5.getCount() == 0
											&& f5.getResult().equalsIgnoreCase(result.getResult());
								}).findAny().isPresent();
							}).findAny().isPresent();

							mapAttrParam.setSelected(true);

//							System.out.println("mapAttrParams " + mapAttrParam.toString());

							if (checkZero) {
								int newCountAttrParam = mapAttrParam.getCount();
								++newCountAttrParam;
								double newProbAttrParam = (double) newCountAttrParam / sumCountResult;
								probResult.set(probResult.get() * newProbAttrParam);

								MapAttribute newMapAttrParam = new MapAttribute(mapAttrParam.getNameAttribute(),
										mapAttrParam.getValueAttribute(), mapAttrParam.getResult(), newCountAttrParam, newProbAttrParam);

								mapAttrParam.setNewMapAttribute(newMapAttrParam);

							} else {
								probResult.set(probResult.get() * mapAttrParam.getProb());
							}

						});
			});

			Result result1 = new Result(result.getValueAttribute(), probResult.get(), false);
			results.add(result1);
		});

		// set isSelected to the highest result
		results.stream().max(Comparator.comparing(Result::getValue)).orElse(null).setSelected(true);

		// set journey of result
		results.stream().forEach(p -> {
			setResultJourney(mapAttrParams, mapAttrResults, p);
		});

		return results;
	}

	@SuppressWarnings("unused")
	private void setResultJourney(List<MapAttribute> mapAttrParams, List<MapAttribute> mapAttrResults, Result result) {
		int sumCountResult = mapAttrResults.stream().mapToInt(MapAttribute::getCount).sum();
		mapAttrResults.stream().filter(f1 -> f1.getValueAttribute().equalsIgnoreCase(result.getName())).forEach(mapAttrResult -> {
//			System.out.println("journey " + mapAttrResult.getValueAttribute());
			AtomicReference<String> origin = new AtomicReference<String>();
			AtomicReference<String> zero = new AtomicReference<String>();
			AtomicReference<String> calculationOrigin = new AtomicReference<String>();
			AtomicReference<String> calculationZero = new AtomicReference<String>();
			AtomicReference<String> prob = new AtomicReference<String>();
			prob.set("(" + mapAttrResult.getCount() + "/" + sumCountResult + ") = " + mapAttrResult.getProb());

			mapAttrParams.stream().filter(f2 -> f2.isSelected()).filter(f3 -> f3.getResult().equalsIgnoreCase(mapAttrResult.getResult()))
					.forEach(mapAttrParam -> {
//						System.out.println("Hola ");
						String attr = mapAttrParam.getNameAttribute();
						String calcProbOrigin = "(" + mapAttrParam.getCount() + "/" + mapAttrResult.getCount() + ") = "
								+ mapAttrParam.getProb();

						if (origin == null)
							origin.set(attr + "-" + calcProbOrigin);
						else
							origin.set(origin.get() + ", " + attr + "-" + calcProbOrigin);

						if (calculationOrigin == null)
							calculationOrigin.set(prob.get().substring(0, prob.get().lastIndexOf('=')).trim() + " x "
									+ calcProbOrigin.substring(0, calcProbOrigin.lastIndexOf('=')).trim());
						else
							calculationOrigin.set(
									calculationOrigin.get() + " x " + calcProbOrigin.substring(0, calcProbOrigin.lastIndexOf('=')).trim());

						if (mapAttrParam.isAnyZeroCount()) {
							String calcProbZero = "(" + mapAttrParam.getNewMapAttribute().getCount() + "/" + sumCountResult + ") = "
									+ mapAttrParam.getNewMapAttribute().getProb();
							if (zero == null)
								zero.set(attr + "-" + calcProbZero);
							else
								zero.set(zero.get() + ", " + attr + "-" + calcProbZero);

							if (calculationZero == null)
								calculationZero.set(prob.get().substring(0, prob.get().lastIndexOf('=')).trim() + " x "
										+ calcProbZero.substring(0, calcProbZero.lastIndexOf('=')).trim());
							else
								calculationZero.set(
										calculationZero.get() + " x " + calcProbZero.substring(0, calcProbZero.lastIndexOf('=')).trim());
						}
					});

			String journey = "=================" + result.getName() + "=================\n";
			journey += "Prob Value: " + prob + "\n";
			journey += "Origin Journey: " + origin + "\n";
			journey += "Origin Journey Calculation: " + calculationOrigin;
			if (zero != null) {
				journey += "\n";
				journey += "Journey because of Laplace Correction: " + zero + "\n";
				journey += "Journey Calculation because of Laplace Correction: " + calculationZero;
			}
			result.setJourney(journey);
		});
	}

	public String validateRequests(MstType type, List<Request> requests) {
		// validate total size
		if (type.getMstMapTypeParameters().size() != requests.size()) {
			return "Total parameters in request and parameters in type " + type.getType() + " are not match";
		}

		// validate match between parameter type and parameter request
		List<MstParameter> params = type.getMstMapTypeParameters().stream().map(m -> m.getMstParameter()).collect(Collectors.toList());
		boolean isMatchParamsAndRequests = requests.stream().allMatch(p -> {
//			System.out.println(p.getNameAttribute());
			return params.stream().anyMatch(f -> {
				System.out.println(f.getName() + " " + String.valueOf(f.getName().equalsIgnoreCase(p.getNameAttribute())));
				return f.getName().equalsIgnoreCase(p.getNameAttribute());
			});
		});

//		System.out.println("isMatchParamsAndRequests: " + isMatchParamsAndRequests);

		if (!isMatchParamsAndRequests) {
			return "Parameters in request and parameters in type " + type.getType() + " are not match";
		}

		return null;
	}

}
