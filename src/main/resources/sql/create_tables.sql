
CREATE TABLE projects(
    id IDENTITY,
    title VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE users(
    id IDENTITY,
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    project_id INTEGER UNIQUE,
    FOREIGN KEY (project_id) REFERENCES projects(id)  
);

CREATE TABLE tasks (
    id IDENTITY,     
    theme VARCHAR(50) NOT NULL UNIQUE,
    taskType VARCHAR(50),
    priority INTEGER,
    description VARCHAR(200),
    user_id INTEGER NOT NULL UNIQUE,
    project_id INTEGER NOT NULL UNIQUE,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (project_id) REFERENCES projects(id)
);






