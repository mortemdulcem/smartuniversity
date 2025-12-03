# Smart University (No Inheritance)

## Run
```bash
javac -d out $(find src -name "*.java")
java -cp out university.Main
```

## Commands
```
help
add-student <id> "<name>" "<major>"
add-course <code> "<title>" <credits> "<instructor>"
enroll <studentId> <courseCode>
grade <studentId> <courseCode> <percent>
gpa <studentId>
list-students | list-courses | list-enrollments
save | load | exit
```

## Data
CSV files under ./data (created on save).
