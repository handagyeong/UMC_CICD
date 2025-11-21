# 1. 베이스 이미지: 자바 17이 깔린 가벼운 환경을 가져온다.
FROM openjdk:17-jdk-slim

# 2. 변수 설정: 그레이들로 빌드하면 보통 이 위치에 jar가 생겨.
# (혹시라도 나중에 jar 파일 못 찾는다고 하면 이 경로를 의심해봐야 해)
ARG JAR_FILE=build/libs/*.jar

# 3. 복사: 빌드된 jar 파일을 도커 컨테이너 안으로 'app.jar'라는 이름으로 복사한다.
COPY ${JAR_FILE} app.jar

# 4. 실행: 도커가 켜지면 이 명령어를 실행한다. (스프링 부트 실행!)
ENTRYPOINT ["java", "-jar", "/app.jar"]