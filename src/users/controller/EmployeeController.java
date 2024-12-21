package users.controller;

import users.models.Employee;
import users.models.User;
import users.view.EmployeeView;
import users.view.UserView;

public class EmployeeController<Model extends Employee, View extends EmployeeView> extends UserController<User, UserView>  {

}
