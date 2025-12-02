# 1. 베이스 이미지: 요즘 국룰인 아마존 코레토 17 버전을 쓴다. (안정성 최고)
FROM amazoncorretto:17

# 2. 변수 설정
ARG JAR_FILE=build/libs/*.jar

# 3. 복사
COPY ${JAR_FILE} app.jar

# 4. 실행
ENTRYPOINT ["java", "-jar", "/app.jar"]