# README

# 설치 및 환경 설정 가이드

### 개발 환경

- 사용 IDE - STS4

    Lombok 설정이 적용된 Eclipse 혹은 STS4를 사용.

- 프로젝트 자바 버전 - 1.8
- 스프링부트 버전 - 2.4.5

### 설치 및 환경 설정

- 해당 깃허브 Repository를 zip 파일로 다운로드 합니다.
![사진1](https://user-images.githubusercontent.com/71741673/117446169-a4c04d80-af76-11eb-8a0f-12daa348e231.PNG)
- 원하는 폴더에 압축을 풉니다.
- STS4 혹은 이클립스에 Gradle project를 import 합니다.
![사진2](https://user-images.githubusercontent.com/71741673/117446181-abe75b80-af76-11eb-96fa-b1d9a4254577.PNG)
![사진3](https://user-images.githubusercontent.com/71741673/117446194-b144a600-af76-11eb-9055-d5539e44bc4d.PNG)
- 폴더 선택후 Next를 클릭하여 import를 finish합니다.
- 프로젝트 빌드가 끝나면 스프링부트를 구동 시킵니다.

# 테이블 생성 SQL

구동시 자동 생성 됩니다.

```json
CREATE TABLE stores (
id int auto_increment primary key,
name varchar(50) not null,
owner varchar(20) NOT NULL,
description varchar(500) NOT NULL,
level int NOT NULL,
address varchar(500),
phone varchar(50)
);

CREATE TABLE businessDays (
id int NOT NULL,
day varchar(30) NOT NULL,
open time not null,
close time not null,
CONSTRAINT FK_storeIdForBusinessDays FOREIGN KEY (id) REFERENCES stores (id) 
ON DELETE CASCADE ON UPDATE CASCADE
);

create table holidays (
id int not null,
holidays datetime not null,
CONSTRAINT FK_storeIdForHolidays FOREIGN KEY (id) REFERENCES stores (id) 
ON DELETE CASCADE ON UPDATE CASCADE
);
```

# API 사용 가이드

- `POST http://localhost:8080/stores` : 점포를 등록합니다.
- `POST http://localhost:8080/stores/holidays` : 해당 점포에 특정 휴무일을 등록합니다.
- `GET http://localhost:8080/stores` : 점포 목록을 조회합니다.
- `GET http://localhost:8080/stores/{id}` : 해당 점포의 상세 정보를 조회합니다.
- `DELETE http://localhost:8080/stores/{id}` : 해당 점포를 삭제합니다
