					-- 1. База данных «Страны и города мира»:
-- 1.1 Сделать запрос, в котором мы выберем все данные о городе – регион, страна.
use geodata;
select _cities.title as city, _regions.title as region, _countries.title as country
from _countries 
	join _cities on (_countries.id = _cities.country_id)
    join _regions on (_regions.id = _cities.region_id)
order by _cities.title, _regions.title , _countries.title
;

-- 1.2 Выбрать все города из Московской области.
use geodata;
select _cities.title as city
from _cities
	join _regions on (_regions.id = _cities.region_id)  and _regions.title = 'Московская область'
    order by _cities.title
;



					-- 2. База данных «Сотрудники»:
-- 2.1 Выбрать среднюю зарплату по отделам.
use employees;
select dept_name as department, avg(salary) as average_salary
from departments
	join dept_emp on (dept_emp.dept_no = departments.dept_no) and dept_emp.to_date = '9999-01-01'
 join salaries on (salaries.emp_no = dept_emp.emp_no) and salaries.to_date = '9999-01-01'
group by dept_name
order by dept_name
;


-- 2.2 Выбрать максимальную зарплату у сотрудника.
use employees;
select dept_name as department, concat(employees.first_name, ' ', employees.last_name) as fio, max(salary) as salary
from departments
	join dept_emp on (dept_emp.dept_no = departments.dept_no) and dept_emp.to_date = '9999-01-01'
    join salaries on (salaries.emp_no = dept_emp.emp_no)
    join employees on (employees.emp_no = salaries.emp_no)
    group by dept_name, concat(first_name, ' ', last_name)
    order by dept_name, max(salary) desc
;


-- 2.3 Удалить одного сотрудника, у которого максимальная зарплата.
use employees;
delete from employees 
where emp_no = (
	select emp_no from salaries  
    where salary = (select max(salary) from salaries)
)
;

-- 2.4 Посчитать количество сотрудников во всех отделах.
use employees;
select count(distinct emp_no) as total 
from dept_emp 
where to_date = '9999-01-01'
;


-- 2.5 Найти количество сотрудников в отделах и посмотреть, сколько всего денег получает отдел.
use employees;
select dept_name as department, count(distinct de.emp_no) as employees, 
		sum(s.salary) as budget, sum(s.salary)/count(distinct de.emp_no) as myAVG, avg(s.salary) as AVG
from dept_emp as de
	join departments as d on (de.dept_no = d.dept_no) and de.to_date = '9999-01-01'
    join salaries as s on (s.emp_no = de.emp_no) and s.to_date = '9999-01-01' 
group by dept_name
order by dept_name
;




