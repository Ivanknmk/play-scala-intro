package controllers

import models.{DB, Person}
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc._

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Hello world")) //200
  }

  val personForm: Form[Person] = Form{
    mapping(
      "name" -> text
    )(Person.apply)(Person.unapply)
      
  }

  def addPerson = Action { implicit request =>
    val person = personForm.bindFromRequest.get
    DB.save(person)
    Redirect(routes.Application.index())
  }
}