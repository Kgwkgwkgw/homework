스펙 정리

- 계좌는 1개의 통화에 대한 잔고를 가질 수 있다.
- 유저가 가질 수 있는 계좌에 대한 제한은 없으나 과제에서는 최대 한 개만 가질 수 있다고 가정한다.



**회원 테이블 (users)**

| 컬럼         | 데이터 타입       | 필수 여부 | 설명                 |
|------------|--------------|-------|--------------------|
| id         | bigint       | O     | PK, Auto Increment |
| email      | varchar(200) | O     | UK, 이메일
| created_at | datetime     | O     | 생성 시간

<br>

**계좌 테이블 (account)**

| 컬럼             | 데이터 타입         | 필수 여부 | 설명                 |
|----------------|----------------|-------|--------------------|
| id             | bigint         | O     | PK, Auto Increment |
| user_id        | bigint         | O     | UK, 회원의 id
| balance        | decimal(38, 5) | O     | 잔고, 소수점있는 통화도 있으므로 정확하게 저장하기 위해 decimal을 사용
| currency       | varchar(20)    | O     | 통화 타입
| account_number | varchar(30)    | O     | UK, 계좌 번호로 유저에게 노출 됌, 클러스터 인덱스를 기준으로 정렬되므로 해당 컬럼을 pk로 잡지 않고 uk로 사용
| created_at     | datetime       | O     | 생성 시간

<br>

**상점 테이블 (merchant)**

| 컬럼         | 데이터 타입      | 필수 여부 | 설명                 |
|------------|-------------|-------|--------------------|
| id         | bigint      | O     | PK, Auto Increment |
| name       | bigint      | O     | 상점 이름
| created_at | datetime    | O     | 생성 시간

**상점 요금 테이블 (merchant_fee)**

| 컬럼          | 데이터 타입        | 필수 여부 | 설명                 |
|-------------|---------------|-------|--------------------|
| id          | bigint        | O     | PK, Auto Increment |
| merchant_id | bigint        | O     | IDX, 상점 id
| currency    | varchar(20)   | O     | 결제 수수료의 통화
| fee         | decimal        | O     | 결제 수수료
| created_at  | datetime      | O     | 생성 시간

<br>

**결제 이력 테이블 (payment_log)**

| 컬럼              | 데이터 타입         | 필수 여부 | 설명                                     |
|-----------------|----------------|-------|----------------------------------------|
| id              | varchar(36)    | O     | PK, 값은 UUID로 설정 
| status          | char(20)       | O     | 결제 상태, READY(결제 준비), APPROVED(승인), FAILED(실패), CANCELD(취소), EXPIRED(만료)                                  
| amount          | decimal(38, 5) | O     | 결제 요금
| payment_method  | varchar(20)    | O     | 결제 방법, CREDIT_CARD(카드), CASH(현금)                             
| payment_details | json           | O     | 각 결제 방법마다 별도의 부가정보
| created_at      | datetime       | O     | 생성 시간                                  


**매출 이력 테이블 (revenue_log)**

| 컬럼             | 데이터 타입      | 필수 여부   | 설명                                            |
|----------------|-------------|---------|-----------------------------------------------|
| revenue_id     | varchar(36) | O       | PK, 값은 UUID로 설정 
| payment_log_id | varchar(36) | O       | UK,payment__log_id 
| merchant_id    | bigint      | O       | 상점 id
| status         | char(20)    | O       | 결제 상태, READY(결제 준비), APPROVED(승인), FAILED(실패) 
| amount         | decimal     | O       |결제 요금  
| created_at     | datetime    | O       | 생성 시간                                         

**매출 이력 테이블 인덱스**

| 인덱스 컬럼                  | 인덱스 타입        | 비고                                            |
|-------------------------|---------------|-----------------------------------------------|
| revenue_id              | Primary key   |  
| payment_log_id          | Unique key    | 
| merchant_id, created_at | Index         | 








