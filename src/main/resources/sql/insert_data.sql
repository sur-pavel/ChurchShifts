INSERT INTO projects (project_name , file) VALUES 
    ('FIRST PROJECT', 'fProject.txt'),
    ('SECOND PROJECT', 'sProject.txt');


INSERT INTO users (first_name, last_name, task_id) VALUES
    ('JAMES', 'SMITH', 1),
    ('JOHN', 'JOHNSON', 1),
    ('ROBERT', 'WILLIAMS', 1),
    ('MICHAEL', 'BROWN', 1),
    ('WILLIAM', 'JONES', 1),
    ('WILLIAM', 'MILLER', 1),
    ('RICHARD', 'DAVIS', 1),
    ('CHARLES', 'GARCIA', 1),
    ('JOSEPH', 'RODRIGUEZ', 1),
    ('THOMAS', 'WILSON', 1),
    ('CHRISTOPHER', 'MARTINEZ', 1),
    ('DANIEL', 'ANDERSON', 1),
    ('PAUL', 'TAYLOR', 2),
    ('MARK', 'THOMAS', 2),
    ('DONALD', 'HERNANDEZ', 2),
    ('GEORGE', 'MOORE', 2),
    ('KENNETH', 'MARTIN', 2),
    ('STEVEN', 'JACKSON', 2),
    ('EDWARD', 'THOMPSON', 2),
    ('BRIAN', 'WHITE', 2),
    ('RONALD', 'LOPEZ', 2),
    ('ANTHONY', 'LEE', 2),
    ('KEVIN', 'GONZALEZ', 2),
    ('JASON', 'HARRIS', 2);

INSERT INTO tasks (project_id, user_id, theme, 
task_type, priority, description) VALUES 
    ('FIRST', 1, 'INITIAL TASK', 'SIMPLE', 2, 'Task for begining'),
    ('SECOND', 2, 'LAST TASK', 'COMPLEX', 1, 'Task for work');
    