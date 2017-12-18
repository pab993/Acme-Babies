/* AdministratorController.java
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CaretakerService;
import services.KindergartenService;
import services.LessonService;
import domain.Caretaker;
import domain.Kindergarten;
import domain.Lesson;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	// Constructors -----------------------------------------------------------
	
	public AdministratorController() {
		super();
	}
		
	// Services
			// =============================================================================

	@Autowired
	KindergartenService kindergartenService;
	
	@Autowired
	LessonService lessonService;
	
	@Autowired
	CaretakerService caretakerService;
		
		// Dashboard
		// =============================================================================

		@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
		public ModelAndView list() {

			ModelAndView result;

			String minAvgMaxLessonsPerKindergarten;
			Collection<Kindergarten> kindergartenWithMoreCustomersInLessons;
			Collection<Kindergarten> kindergartenWithLessCustomersInLessons;
			Collection<Caretaker> caretakerWithMoreAssessments;
			Collection<Lesson> lessonWithMoreAssessments;
			Collection<Object[]> rankingCaretakersAcceptedOffers;
			Collection<Kindergarten> rankingKindergartensCreatedLessons;
			
			if(kindergartenService.findAll().isEmpty() || lessonService.findAll().isEmpty()){
				minAvgMaxLessonsPerKindergarten = "Min: 0, Avg: 0, Max: 0";
			}else{
				minAvgMaxLessonsPerKindergarten = lessonService.getMinAvgMaxLessonsPerKindergarten();
			}
			
			
			kindergartenWithMoreCustomersInLessons = kindergartenService.getKindergartenWithMoreCustomersInLessons();

			if(kindergartenWithMoreCustomersInLessons.isEmpty()){
				
				kindergartenWithMoreCustomersInLessons = new HashSet<Kindergarten>();
			}
			
			kindergartenWithLessCustomersInLessons = kindergartenService.getKindergartenWithLessCustomersInLessons();
			
			if(kindergartenWithLessCustomersInLessons.isEmpty()){
				
				kindergartenWithLessCustomersInLessons = new HashSet<Kindergarten>();
			}
			
			caretakerWithMoreAssessments = caretakerService.getCaretakerWithMoreAssessments();
			
			if(caretakerWithMoreAssessments.isEmpty()){
				
				caretakerWithMoreAssessments = new HashSet<Caretaker>();
			}
			
			lessonWithMoreAssessments = lessonService.getLessonWithMoreAssessments();
			
			if(lessonWithMoreAssessments.isEmpty()){
				
				lessonWithMoreAssessments = new HashSet<Lesson>();
			}
			
			rankingCaretakersAcceptedOffers = caretakerService.getRankingCaretakersAcceptedOffers();
			
			if(rankingCaretakersAcceptedOffers.isEmpty()){
				
				rankingCaretakersAcceptedOffers = new HashSet<Object[]>();
			}
			
			rankingKindergartensCreatedLessons = kindergartenService.getRankingKindergartensCreatedLessons();
			
			if(rankingKindergartensCreatedLessons.isEmpty()){
				
				rankingKindergartensCreatedLessons = new HashSet<Kindergarten>();
			}
			
			
			result = new ModelAndView("administrator/dashboard");
			
			result.addObject("minAvgMaxLessonsPerKindergarten",
					minAvgMaxLessonsPerKindergarten);
			result.addObject("kindergartenWithMoreCustomersInLessons",
					kindergartenWithMoreCustomersInLessons);
			result.addObject("kindergartenWithLessCustomersInLessons",
					kindergartenWithLessCustomersInLessons);
			result.addObject("caretakerWithMoreAssessments",
					caretakerWithMoreAssessments);
			result.addObject("lessonWithMoreAssessments",
					lessonWithMoreAssessments);
			result.addObject("rankingCaretakersAcceptedOffers",
					rankingCaretakersAcceptedOffers);
			result.addObject("rankingKindergartensCreatedLessons",
					rankingKindergartensCreatedLessons);
			
			result.addObject("requestURI", "administrator/dashboard.do");

			return result;
		}
	
	
}