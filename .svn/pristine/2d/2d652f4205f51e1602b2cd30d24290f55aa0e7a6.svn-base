
package controllers;

import java.util.Collection;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.ConfigurationSystemService;
import services.LessonService;
import domain.Actor;
import domain.Assessment;
import domain.ConfigurationSystem;
import domain.Lesson;

@Controller
@RequestMapping("/lesson")
public class LessonController extends AbstractController {

	// Services
	// ============================================================================

	@Autowired
	private LessonService				lessonService;

	@Autowired
	private ActorService				actorService;

	@Autowired
	private ConfigurationSystemService	configurationSystemService;


	// Constructors
	// ============================================================================

	public LessonController() {
		super();
	}

	// List
	// =============================================================================

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Lesson> lessons;
		Date now;
		Boolean display;

		now = new Date(System.currentTimeMillis());
		lessons = this.lessonService.findAll();
		display = true;

		result = new ModelAndView("lesson/list");
		result.addObject("lessons", lessons);
		result.addObject("now", now);
		result.addObject("display", display);
		result.addObject("requestURI", "lesson/list.do");

		return result;
	}

	// List creadas por kindergarten
	// =============================================================================

	@RequestMapping(value = "/myList", method = RequestMethod.GET)
	public ModelAndView myList() {
		ModelAndView result;
		Collection<Lesson> lessons;
		Actor actor;

		actor = this.actorService.findByPrincipal();

		lessons = this.lessonService.findAllByKindergarten(actor);

		result = new ModelAndView("lesson/listKindergarten");
		result.addObject("lessons", lessons);
		result.addObject("actor", actor);
		result.addObject("requestURI", "lesson/myList.do");

		return result;
	}

	// List customer
	// =============================================================================

	@RequestMapping(value = "/customer/list", method = RequestMethod.GET)
	public ModelAndView listByCustomer() {
		ModelAndView result;
		Collection<Lesson> lessons;
		Actor actor;

		actor = this.actorService.findByPrincipal();

		lessons = this.lessonService.findAllByCustomer(actor);

		result = new ModelAndView("lesson/list");
		result.addObject("lessons", lessons);
		result.addObject("requestURI", "lesson/customer/list.do");

		return result;
	}

	// List kindergarten
	// =============================================================================

	@RequestMapping(value = "/kindergarten/list", method = RequestMethod.GET)
	public ModelAndView listKindergarten() {
		ModelAndView result;
		Collection<Lesson> lessons;
		Actor actor;

		actor = this.actorService.findByPrincipal();
		lessons = this.lessonService.findAll();

		result = new ModelAndView("lesson/listKindergarten");
		result.addObject("lessons", lessons);
		result.addObject("actor", actor);
		result.addObject("requestURI", "lesson/kindergarten/list.do");

		return result;
	}

	// Display
	// ============================================================================

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int lessonId) {

		ModelAndView result;
		Lesson lesson;

		try {
			lesson = this.lessonService.findOne(lessonId);
			Assert.notNull(lesson);

			final Collection<Assessment> assessments = lesson.getAssessments();

			result = new ModelAndView("lesson/display");
			result.addObject("lesson", lesson);
			result.addObject("assessments", assessments);
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/panic/misc.do");
		}
		return result;
	}
	// Create
	// =================================================================================================

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		final Lesson lesson = this.lessonService.create();

		result = this.createEditModelAndView(lesson);

		return result;
	}

	// Edition
	// =============================================================================

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int lessonId) {
		ModelAndView result;

		try {
			final Lesson lesson = this.lessonService.findOne(lessonId);
			Assert.notNull(lesson);
			Assert.isTrue(lesson.getKindergarten().equals(this.actorService.findByPrincipal()));

			result = this.createEditModelAndView(lesson);

		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/panic/misc.do");
		}

		return result;

	}

	// Save
	// =============================================================================

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Lesson lesson, final BindingResult binding) {
		ModelAndView result;

		try {

			if (binding.hasErrors())
				result = this.createEditModelAndView(lesson, "lesson.save.error");
			else {
				result = new ModelAndView("redirect:/lesson/kindergarten/list.do");
				this.lessonService.save(lesson);

			}
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(lesson, "lesson.save.error");
		}

		return result;
	}

	// Deleting
	// -------------------------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Lesson lesson, final BindingResult binding) {
		ModelAndView result;

		try {
			this.lessonService.delete(lesson);
			result = new ModelAndView("redirect:/lesson/kindergarten/list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(lesson, "lesson.delete.error");
		}
		return result;
	}

	// SearchForm
	// ==============================================================================

	@RequestMapping(value = "/searchForm", method = RequestMethod.GET)
	public ModelAndView listSearch() {
		ModelAndView result;
		Collection<Lesson> lessons;
		Boolean firstTime;

		lessons = this.lessonService.findAll();
		firstTime = true;

		result = new ModelAndView("lesson/search");
		result.addObject("lessons", lessons);
		result.addObject("requestURI", "lesson/searchForm.do");
		result.addObject("firstTime", firstTime);

		return result;
	}

	// Search
	// ==============================================================================

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView search(@RequestParam("keyword") final String keyword) {
		ModelAndView result;
		Collection<Lesson> lessons;

		lessons = this.lessonService.getLessonByKeyWord(keyword);

		result = new ModelAndView("lesson/search");
		result.addObject("lessons", lessons);

		return result;
	}
	// Ancilliary methods
	// =================================================================================================

	private ModelAndView createEditModelAndView(final Lesson lesson) {

		return this.createEditModelAndView(lesson, null);
	}

	private ModelAndView createEditModelAndView(final Lesson lesson, final String message) {

		Collection<ConfigurationSystem> configurationSystems;
		ConfigurationSystem configurationSystem;
		Double lessonTax;

		configurationSystems = this.configurationSystemService.findAll();
		configurationSystem = new ConfigurationSystem();

		for (final ConfigurationSystem cs : configurationSystems) {
			configurationSystem = cs;
			break;
		}

		lessonTax = configurationSystem.getLessonTax();

		final ModelAndView resul = new ModelAndView("lesson/edit");

		resul.addObject("lesson", lesson);
		resul.addObject("lessonTax", lessonTax);
		resul.addObject("message", message);

		return resul;
	}

}
