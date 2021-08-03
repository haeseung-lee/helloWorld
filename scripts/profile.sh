#!/user/bin/env bash

# 쉬고있는 profile 찾기 : real1이 사용 중이면 real2가 쉬고 있고, 반대면 real1 이 쉬고 있음

function find_idle_profile()
{
  # 현재 엔진엑스가 바라보고 있는 스프링 부트가 정상적으로 수행중인지 혹인
  # 응답값을 HttpStatus로 받음
  RESPONSE_CODE=$(curl -s -o /dev/null -w "%{http_code}" http://localhost/profile)

  if [ ${RESPONSE_CODE} -ge 400 ]  # 400 보다 크면(즉, 40x/50x 에러 모드 포함)

  then
    CURRENT_PROFILE=real2 # 엔진엑스와 연결되지 않은 profile
  else
    CURRENT_PROFILE=$(curl -s http://localhost/profile)
  fi

  if [ ${CURRENT_PROFILE} == real1 ]
  then
    echo "> ${CURRENT_PROFILE}"
    IDLE_PROFILE=real2
  else
    echo "> ${CURRENT_PROFILE}"
    IDLE_PROFILE=real1
  fi

  # bash 스크립트는 값을 반화하는 기능이 없음
  # 그래서 제일 마지막 줄에 echo로 결과를 출력 후, 클라이언트에서 그 값을 잡아서 사용
  # 중간에 사용하면 안됨
  echo "${IDLE_PROFILE}"
}

# 쉬고 있는 profile의 port찾기
function find_idle_port() {
    IDLE_PROFILE=$(find_idle_profile)

    if [ ${IDLE_PROFILE} == real1 ]
    then
      echo "8081"
    else
      echo "8082"
    fi
}