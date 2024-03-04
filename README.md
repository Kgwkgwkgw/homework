스펙 정리

- 계좌는 1개의 통화에 대한 잔고를 가질 수 있다.
- 유저가 가질 수 있는 계좌에 대한 제한은 없으나 과제에서는 최대 한 개만 가질 수 있다고 가정한다.
- 결제 상태의 흐름은 다음과 같음
- 준비(READY) -> APPROVED (승인) -> CANCELED (취소)
  -> FAILED (실패)
  -> EXPIRED (만료)

개념 정리
- 거래는 결제보다 더 넓은 개념입니다. 거래는 결제 승인(상품·서비스와 돈을 맞바꾸는 것) 외에도 결제 취소, 부분 취소를 포함합니다.
- VAN은 데이터를 카드사와 안전하고 정확하게 연결해주는 기능
- PG는 결제 + 부가 기능(정산)


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

| 컬럼         | 데이터 타입      | 필수 여부 | 설명               |
|------------|-------------|-------|------------------|
| id         | varchar(40) | O     | PK |
| name       | bigint      | O     | 상점 이름
| created_at | datetime    | O     | 생성 시간

**상점 수수료 테이블 (merchant_fee)**

| 컬럼          | 데이터 타입        | 필수 여부 | 설명                 |
|-------------|---------------|-------|--------------------|
| id          | bigint        | O     | PK, Auto Increment |
| merchant_id | bigint        | O     | IDX, 상점 id
| currency    | varchar(20)   | O     | 결제 수수료의 통화
| fees        | decimal        | O     | 결제 수수료
| created_at  | datetime      | O     | 생성 시간


**상점의 상품 테이블 (product)**

| 컬럼         | 데이터 타입      | 필수 여부 | 설명               |
|------------|-------------|-------|------------------|
| id         | varchar(40) | O     | PK |
| name       | bigint      | O     | 상품 이름
| created_at | datetime    | O     | 생성 시간


**주문 테이블 (order)**

| 컬럼         | 데이터 타입      | 필수 여부  | 설명               |
|------------|----------------|----------|------------------|
| id         | varchar(40)    | O        | PK |
<br>

**결제 로그 테이블 (payment_log)**

| 컬럼                  | 데이터 타입         | 필수 여부   | 설명                                     |
|---------------------|----------------|---------|----------------------------------------|
| id                  | varchar(36)    | O       | PK, 값은 UUID로 설정 
| payment_status      | char(20)       | O       | 결제 상태, READY(결제 준비), APPROVED(승인), FAILED(실패), CANCELED(취소), EXPIRED(만료)
| user_id             | bigint         | O       | user id
| merchant_id         | bigint         | O       | 상점 id
| amount              | decimal(38, 5) | O       | 결제 요금
| currency            | decimal(38, 5) | O       | 결제 통화
| balance_amount      | decimal(38, 5) | O       | 취소 가능 금액
| payment_method      | varchar(20)    | O       | 결제 방법, CREDIT_CARD(카드), CASH(현금)                             
| extra               | json           | O       | 각 결제 방법마다 별도의 부가정보
| approved_at         | datetime       | O       | 결제 승인 시간
| last_canceled_at    | datetime       | O       | 마지막 결제 취소 시간
| last_faled_at       | datetime       | O       | 마지막 결제 실패 시간
| last_transaction_at | datetime       | O       | 마지막 거래 시간
| failed_cnt          | int            | O       | 결제 실패 카운트
| reuqest_at          | datetime       | O       | 결제 요청 시간


**거래 이력 테이블 (transaction_log)**
거래는 transaction_at으로 테이블 파티셔닝하고, 주기적으로 삭제함
결제당 최소 1개가 존재함

| 컬럼                 | 데이터 타입         | 필수 여부   | 설명                                     |
|--------------------|----------------|---------|----------------------------------------|
| id                 | bigint         | O       | PK, Auto Increment
| payment_log_id     | varchar(36)    | O       | 결제 id                                  
| amount             | decimal(38, 5) | O       | 요금
| transaction_status | char(20)       | O       | 거래 상태, READY(결제 준비), APPROVED(승인), FAILED(실패), CANCELED(취소), EXPIRED(만료)
| transaction_at     | datetime       | O       | 거래 시간 

**거래 이력 테이블 인덱스**

| 인덱스 컬럼             | 인덱스 타입      | 비고                                            |
|--------------------|-------------|-----------------------------------------------|
| id, transaction_at | Primary key |
| payment_log_id     | IDX         |





