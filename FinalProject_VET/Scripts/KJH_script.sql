----------------------------------- 진료예약 --------------------------------------------
------------------------------------- 조회 --------------------------------------------
-- 월별 - 예약확정(Y) + 예약취소(N) 건수 조회

SELECT  MM, NVL(Y_COUNT, 0) AS Y_COUNT
	FROM(
		SELECT "MONTH", COUNT(*) AS Y_COUNT
			FROM (
				SELECT TO_CHAR(RESRV_VISIT, 'MM') AS "MONTH" , RESRV_HOPS ,  RESRV_STATUS 
					FROM RESERVATION r 
					WHERE TO_CHAR(RESRV_VISIT, 'YYYY') = '2023'
						AND RESRV_STATUS ='Y'
						AND RESRV_HOPS = 'gana@naver.com'
			)
			GROUP BY "MONTH"
		) M1
	RIGHT JOIN 
		(SELECT LPAD(LEVEL,2,0) MM
			FROM DUAL
			CONNECT BY LEVEL <=12) M2
	ON M1."MONTH" = M2.MM
	ORDER BY MM;

-- 해당 월에 등록된 예약자 명단	
SELECT RESRV_VISIT, RESRV_NUM, RESRV_NAME  
	FROM RESERVATION r 
	WHERE TO_CHAR(RESRV_VISIT, 'YYYY') = '2023' 
		AND TO_CHAR(RESRV_VISIT, 'MM') = '09' 
		AND RESRV_HOPS = 'gana@naver.com'
		AND RESRV_STATUS = 'Y';
	


-- 날짜 선택 시 해당 날짜에 등록된 확정예약 건수 조회
SELECT COUNT(*) 
	FROM RESERVATION r 
	WHERE RESRV_VISIT = '2023-09-09'
		AND RESRV_HOPS = 'gana@naver.com'
		AND RESRV_STATUS = 'Y';
	
-- 일별 - 각 시간대에 예약확정된 예약자 조회
SELECT RESRV_NAME , RESRV_TIME, RESRV_VISIT 
	FROM RESERVATION r 
	WHERE RESRV_VISIT = '2023-09-09'
		AND RESRV_HOPS = 'gana@naver.com'
		AND RESRV_STATUS = 'Y';


-- 예약 상세정보를 조회할 수 있다.
SELECT RESRV_NUM, RESRV_NAME, RESRV_TEL, TO_CHAR(RESRV_VISIT,'YYYYMMDD') AS RESRV_VISIT, RESRV_TIME, RESRV_MEMO 
	FROM RESERVATION r 
		WHERE RESRV_NUM = 'RSV8';
	
-- 일별 예약상태에 따라 조회할 수 있다.
SELECT RESRV_NUM, RESRV_TIME, RESRV_NAME, RESRV_TEL, RESRV_STATUS 
	FROM RESERVATION r 
	WHERE RESRV_VISIT = '2023-09-09'
		AND RESRV_HOPS = 'gana@naver.com'
		AND RESRV_STATUS = 'W';

------------------------------------- 등록 --------------------------------------------
-- 진료예약 등록 (예약번호, 예약대기, 예약자 이름, 전화번호 , 예약시간, 메모, 병원ID)
-- - default값: 예약대기(W)상태
INSERT INTO RESERVATION (RESRV_NUM, 
						RESRV_HOPS, RESRV_VISIT, RESRV_TIME, 
						RESRV_NAME, RESRV_TEL, RESRV_MEMO, 
						RESRV_STATUS, RESRV_REGDATE) 
	VALUES((SELECT CONCAT('RSV',NVL(MAX(TO_NUMBER(SUBSTR(RESRV_NUM, 4))),0)+1) AS RESRV_NUM FROM RESERVATION r), 
			'nada@naver.com', '2023-09-10', '14', 
			'최메리다', '01098765430', '메모 입력 테스트', 
			'W',(SYSDATE));		

	
------------------------------------- 수정 --------------------------------------------
-- 병원관계자의 승인 시 예약대기(W)에서 예약확정(Y) 상태로 변경
UPDATE RESERVATION 
	SET RESRV_STATUS = 'Y'  
		WHERE RESRV_NUM='RSV1';
	
	
-- 환불처리가 된 예약은 예약확정(Y) 상태에서 예약취소(N) 상태로 변경
UPDATE RESERVATION 
	SET RESRV_STATUS = 'N'  
		WHERE RESRV_NUM='RSV1';
	


------------------------------------- 삭제 --------------------------------------------		
-- 예약을 거절/취소한 경우 예약목록에서 삭제
	DELETE FROM RESERVATION WHERE RESRV_NUM='RSV21';


SELECT *
	FROM RESERVATION r ;

---------------------------------------------------------------------------------

----------------------------------- 지도 --------------------------------------------
-- 동물병원명을 검색하여 동물병원 목록조회 
SELECT HOSP_NAME , HOSP_MNG , USERS_TEL , USERS_ADDR
	FROM (SELECT h.HOSP_NAME , h.HOSP_MNG , u.USERS_TEL , u.USERS_ADDR, 
				ROW_NUMBER () OVER(ORDER BY HOSP_NAME) RN
			FROM HOSPITAL h JOIN USERSINFO u 
				ON h.HOSP_ID = u.USERS_ID 
			WHERE HOSP_NAME LIKE '%동물%')
	WHERE RN BETWEEN '1' AND '5';

-- 주소 선택(시/도, 시/군/구) 하여 해당 지역의 동물병원 위치 지도에 표시
SELECT h.HOSP_NAME , HOSP_PARK , u.USERS_TEL , u.USERS_ADDR 
	FROM USERSINFO u JOIN HOSPITAL h
		ON h.HOSP_ID = u.USERS_ID 
	WHERE u.USERS_ADDR LIKE '%금천구%';

-- 진료가능 동물 / 진료과목 / 운영형태 / 주차 선택하여 동물병원 위치표시
-- 운영형태
SELECT h.HOSP_NAME, u.USERS_ADDR , u.USERS_TEL  
	FROM HOSPITAL h 
	JOIN USERSINFO u ON h.HOSP_ID = u.USERS_ID 
	WHERE h.HOSP_MNG = 'E' ;

-- 주차가능
SELECT h.HOSP_NAME, u.USERS_ADDR , u.USERS_TEL  
	FROM HOSPITAL h 
	JOIN USERSINFO u ON h.HOSP_ID = u.USERS_ID 
	WHERE h.HOSP_PARK = 'Y';  

-- 운영형태 + 주차가능 (다중선택)
SELECT h.HOSP_NAME, u.USERS_ADDR , u.USERS_TEL  
	FROM HOSPITAL h 
	JOIN USERSINFO u ON h.HOSP_ID = u.USERS_ID 
	WHERE h.HOSP_MNG = 'G'
		AND h.HOSP_PARK = 'Y'; 
	
-- 진료가능 동물 (단일선택)
SELECT h.HOSP_NAME, u.USERS_ADDR , a.ANM_CODE 
	FROM HOSPITAL h 
	JOIN USERSINFO u ON h.HOSP_ID = u.USERS_ID 
	JOIN ANIMALCONNECT a ON a.HOSP_ID = h.HOSP_ID
	WHERE a.ANM_CODE = 'D';

-- 진료과목 (단일선택)
SELECT h.HOSP_NAME, u.USERS_ADDR , m.MEDI_CODE 
	FROM HOSPITAL h 
	JOIN USERSINFO u ON h.HOSP_ID = u.USERS_ID 
	JOIN MEDICONNECT m ON m.HOSP_ID = h.HOSP_ID 
	WHERE m.MEDI_CODE = '00';


SELECT *
	FROM HOSPITAL h ;
SELECT *
	FROM USERSINFO u 
	WHERE USERS_AUTH = 'H';