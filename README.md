# Quiz Application

A Spring Boot-based Quiz Application that supports user sessions, random question generation, and answer submission with validation.

## Features
- Create user sessions.
- Retrieve random questions.
- Submit answers and validate correctness.
- View session results.

## Running the Project
1. Clone the repository:
   ```bash
   git clone <repository_url>
   ```
2. Build the project:
   ```bash
   mvn clean install
   ```
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```
4. Access the API at:
   [http://localhost:8080/api/quiz](http://localhost:8080/api/quiz)

## API Endpoints

### 1. **Create a Session**
**Endpoint**:  
`POST /api/quiz/sessions`

**Example**:  
`POST http://localhost:8080/api/quiz/sessions`

**Request Body**:  
```json
{
  "userId": 1
}
```

**Response**:  
```json
{
    "sessionId": 7394636672097733124
}
```

### 2. **Get a Random Question**
**Endpoint**:  
`GET /api/quiz/sessions/{sessionId}/users/{userId}/questions/random`

**Example**:  
`GET http://localhost:8080/api/quiz/sessions/1862880240315286309/users/1/questions/random`

**Response**:  
```json
{
    "questionId": 16,
    "questionStatement": "Which countries are part of the United Kingdom?",
    "options": [
        "England",
        "France",
        "Scotland",
        "Ireland"
    ]
}
```

### 3. **Submit an Answer**
**Endpoint**:  
`POST /api/quiz/sessions/{sessionId}/answer`

**Example**:  
`POST http://localhost:8080/api/quiz/sessions/7394636672097733124/answer`

**Request Body**:  
```json
{
  "userId": 1,
  "questionId": 16,
  "responses": [0]
}
```

**Response**:  
```json
{
    "answerDetails": {
        "questionId": 16,
        "remarks": "You have answered wrongly. Correct answers are: England Scotland ",
        "correct": false
    }
}
```

### 4. **Get Session Results**
**Endpoint**:  
`GET /api/quiz/sessions/{sessionId}/results`

**Example**:  
`GET http://localhost:8080/api/quiz/sessions/7394636672097733124/results`

**Response**:  
```json
{
    "correctAnswers": 1,
    "wrongAnswers": 1,
    "answerDetails": [
        {
            "questionId": 16,
            "remarks": "You have answered wrongly. Correct answers are: England Scotland ",
            "correct": false
        },
        {
            "questionId": 21,
            "remarks": "You have answered correctly. Correct answers are: Evaporation Condensation Precipitation ",
            "correct": true
        }
    ]
}
```

## TODO
1. Add error handling in `QuizController`.
2. Store sessions and results in a database.
3. Add logging for better traceability.

