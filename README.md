# 9TH-SPRING-A

### 📌 Commit Convention
**[태그] 제목의 형태**

| 태그 이름 | 설명 |
|-----------|------|
| FEAT      | 새로운 기능을 추가할 경우 |
| FIX       | 버그를 고친 경우 |
| CHORE     | 짜잘한 수정 |
| DOCS      | 문서 수정 |
| INIT      | 초기 설정 |
| TEST      | 테스트 코드, 리펙토링 테스트 코드 추가 |
| RENAME    | 파일 혹은 폴더명을 수정하거나 옮기는 작업인 경우 |
| STYLE     | 코드 포맷팅, 세미콜론 누락, 코드 변경이 없는 경우 |
| REFACTOR  | 코드 리팩토링 |



### 커밋 타입
- `[태그] 설명` 형식으로 커밋 메시지를 작성합니다.
- 태그는 영어를 쓰고 대문자로 작성합니다.

예시 >
```
  [FEAT] 검색 api 추가
```



# 🚀 **umc-9th-cicd**

 umc 9기 배포(CI/CD)와 관련된 모든 자동화 및 설정을 관리합니다.

- 코드 자동 빌드 및 배포
- GitHub Actions 등 CI/CD 파이프라인 구성
- 배포 관련 스크립트, 설정파일, 문서 관리

## 📁 R**eference**

https://velog.io/@jayaione_ele/CICD-Docker-Github-Actions-EC2-RDS-SpringBoot-Flask-%EB%B0%B0%ED%8F%AC-1

https://velog.io/@jayaione_ele/CICD-https-nginx-%EA%B0%80%EB%B9%84%EC%95%84-%EB%8F%84%EB%A9%94%EC%9D%B8-%EC%97%B0%EA%B2%B0%ED%95%98%EA%B8%B0

https://velog.io/@tilsong/%EC%BD%94%EB%93%9C-Push%EB%A1%9C-%EB%B0%B0%ED%8F%AC%EA%B9%8C%EC%A7%80-Github-Actions-Docker

## 🔧 Tool Stack

- Backend: Spring Boot
- Database: AWS RDS (MySQL)
- CI/CD Tool: GitHub Actions
- Containerization: Docker, Docker Hub (또는 AWS ECR)
- Deployment: AWS EC2 (Ubuntu)
- Storage: AWS S3 (이미지/정적 파일 업로드)

## **🌐 Git Convention : GitHub Flow**

- **브랜치 종류** : **`main`**, **`feature`**

**1. main**

- **Production 환경**에 언제 배포해도 문제없는 안정(stable) 브랜치입니다.
- 장애나 긴급 버그 발생 시 **main 브랜치**에서 핫픽스를 진행합니다.
- Initial commit을 제외하고 **main 브랜치에 직접 커밋하지 않으며**, 반드시 Pull Request로만 병합합니다.

**2. feature**

- **이슈 기반**으로 브랜치를 생성하며, 브랜치명은 반드시 **`feature/{이슈번호}-{기능명}`** 형식을 따릅니다.
    
    예: **`feature/1-user-authentication`**
    
- 기능 개발과 관련된 버그 수정은 feature 브랜치 내에서 마무리한 뒤, **main 브랜치로 PR**을 올립니다.

## **📝 Git Convention : Commit**

커밋 메시지는 **제목, 본문, 꼬리말** 세 부분으로 나누며, 각 부분은 **빈 줄로 구분**합니다.

**1. 제목(Title)**

- 형식: **`Tag: 제목`**
- Tag의 첫 글자는 **대문자**, 콜론(**`:`**) 뒤 한 칸 띄우고 제목 작성
- 예:
    
    `TextFeat: 로그인 기능 추가`
    

**2. Tag 종류**

| **Tag** | **설명** |
| --- | --- |
| Feat | 새로운 기능 추가 |
| Fix | 버그 수정 |
| Docs | 문서 수정 |
| Style | 코드 포맷/스타일 변경 |
| Refactor | 코드 리팩토링 |
| Test | 테스트 코드 추가/수정 |
| Chore | 빌드/설정/패키지 관련 작업 |
| Merge | 브랜치 병합 |

**3. 본문(Body)**

- **72자 이내**로 줄바꿈하여 작성
- 무엇을, 왜 변경했는지 **구체적으로 설명**
- 예:
    
    `text로그인 API에 JWT 토큰 발급 로직 추가
    
    - 로그인 성공 시 액세스 토큰과 리프레시 토큰 발급
    - 토큰 유효시간 설정 및 DB 저장 로직 구현`

**4. 꼬리말(Footer)**

- 형식: **`Type: #이슈번호`** (필수 아님)
- 예:
    
    `textFixes: #12`
