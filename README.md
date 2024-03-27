### 수동 배포 방법
1. 코드 수정 후 빌드(key와 yml 포함해야함)
```shell
(local)
 
> ./gradlew bootJar
```

2. SCP 로 EC2 인스턴스에 jar 파일 전송
```shell
(local)

> scp -i {pem 경로} ./build/libs/ssdam_server-0.0.1-SNAPSHOT.jar ubuntu@{EC2 인스턴스 ip}:~/ssdam
```

3. EC2 인스턴스 SSH
```shell
(local)

> ssh -i {pem 경로} ubuntu@{EC2 인스턴스 ip}
```

4. 기존 프로세스 종료 후 재실행
```shell
(ec2 instance)

> jobs
> fg %1 (jobs 에서 스프링 job 인덱스, 거의 무조건 1)

ctrl + c 로 프로세스 종료

> cd ~/ssdam
> nohup java -jar ssdam_server-0.0.1-SNAPSHOT.jar &

서버 로그 확인
> tail -f nohub.out
```

### 복구하면서 참고사항
* 기존에 PK를 이상하게 복합키로 해놓았을 것이라 추측되는 테이블들은 복구하면서 PK 안걸었으니 참고할 것.
* 기타 각종 제약(NOT NULL, FK)들도 혹시 몰라서 최대한 걸지 않음.
* AWS ALB에서 SSL Termination 하도록 변경해서 그 부분 체크하는 스프링 시큐리티 설정 제거.
* SSL 설정의 편리함을 위해 로드밸런서 사용했는데 비용이 발생하므로 EC2 내부에 Nginx 설치하는 방식도 고려볼 것.
* 배포 방식도 일단 제일 빠르고 편한 방법으로 하긴 했는데 추후 개선하는 편이 좋을 듯.