INSERT INTO employee (email, name, password, username, manager_employee_id) VALUES 
('John', 'john@mail.com', 'john', 'password1', NULL);
INSERT INTO employee (email, name, password, username, manager_employee_id) VALUES 
('Mary', 'mary@mail.com', 'mary', 'password1', 1);
INSERT INTO employee (email, name, password, username, manager_employee_id) VALUES 
('Peter', 'peter@mail.com', 'peter', 'password1', 1);

INSERT INTO role (name) VALUES ('Employee');
INSERT INTO role (name) VALUES ('Manager');
INSERT INTO role (name) VALUES ('Administrator');

INSERT INTO employee_roles (employees_employee_id, roles_role_id) VALUES (1, 1);
INSERT INTO employee_roles (employees_employee_id, roles_role_id) VALUES (2, 1);
INSERT INTO employee_roles (employees_employee_id, roles_role_id) VALUES (3, 1);
INSERT INTO employee_roles (employees_employee_id, roles_role_id) VALUES (1, 2);
INSERT INTO employee_roles (employees_employee_id, roles_role_id) VALUES (2, 3);