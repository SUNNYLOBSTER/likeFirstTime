SELECT u.USERS_NAME AS users_name, q.QST_TITLE, q.QST_CONTENT,
	 q.QST_REGDATE, r.RPY_SEQ, r.RPY_ID, r.RPY_REF, 
	 r.RPY_CONTENT , r.RPY_STATUS, r.RPY_REGDATE
	FROM QUESTBOARD q, USERSINFO u, REPLYBOARD r, HOSPITAL h
	WHERE q.QST_ID = u.USERS_ID 
	AND q.QST_SEQ = r.RPY_REF
	AND r.RPY_ID = h.HOSP_ID
	AND (q.QST_STATUS = 'Y' OR r.RPY_STATUS = 'Y')
	AND q.QST_SEQ = '#{qst_seq}'
    ORDER BY r.RPY_SEQ DESC;
    
   SELECT q.*, r.*
	FROM USERSINFO u 
	inner join QUESTBOARD q on q.QST_ID = u.USERS_ID 
	left join REPLYBOARD r on q.QST_SEQ = r.RPY_REF	
	left join HOSPITAL h on h.HOSP_ID = r.RPY_ID
	where
	(q.QST_STATUS != 'N' OR r.RPY_STATUS != 'N')
	 and q.QST_SEQ = 'Q9'
    ORDER BY r.RPY_SEQ DESC;
    
   
   SELECT q.QST_SEQ, q.QST_ID, u.USERS_NAME, q.QST_TITLE, q.QST_CONTENT, q.QST_SPECIES, q.QST_PART, q.QST_REGDATE, q.QST_FAST
	FROM QUESTBOARD q JOIN USERSINFO u  
	ON q.QST_ID = u.USERS_ID 
	WHERE q.QST_STATUS != 'N'
    ORDER BY q.QST_FAST DESC, q.QST_REGDATE DESC ;
   
   
   SELECT q.QST_ID, u.USERS_NAME
	FROM USERSINFO u JOIN QUESTBOARD q 
	ON q.QST_ID = u.USERS_ID;
	

SELECT q.QST_SEQ, q.QST_ID, u.USERS_NAME, q.QST_TITLE, q.QST_CONTENT, q.QST_SPECIES, ANM_SPECIES, q.QST_PART, q.QST_REGDATE, q.QST_FAST
	FROM QUESTBOARD q JOIN USERSINFO u  
	ON q.QST_ID = u.USERS_ID 
	JOIN ANIMALCODE a
	ON q.QST_SPECIES = a.ANM_CODE
	WHERE q.QST_STATUS != 'N'
    ORDER BY q.QST_FAST DESC, q.QST_REGDATE DESC ;
    
   
-- 미리보기에서 글자 수 초과 시 ... 적용을 위한 더미데이터 
INSERT INTO QUESTBOARD
			(QST_SEQ, QST_ID, QST_TITLE, 
			QST_CONTENT, QST_SPECIES, QST_PART,
			QST_REGDATE, QST_FAST, QST_STATUS)
	VALUES(
			(SELECT CONCAT('Q', NVL(MAX(TO_NUMBER(SUBSTR(QST_SEQ,2))), 0)+1) AS QST_SEQ FROM QUESTBOARD),
			'elsa@disney.com', '제목에는 ...이 붙지 않는다 ', 
			'질문이 있습니다. 중요한 질문입니다. 하지만 급한 질문은 아닙니다. 질문의 중요성이 반드시 긴급성과 비례해야 한다고 생각하지는 않습니다. 저에게는 강아지가 한 마리 있습니다. 고양이일 수도 있습니다. 사실은 코드를 확인하지 않아서 기억이 나지 않습니다. 하지만 그것 또한 중요한 게 아닙니다. 이것은 질문이 아니기 때문입니다. 질문에는 답변이 있어야 하지만 질문이 아니라면 답변이 필요 없습니다. 개인가 고양이인가는 관측하는 순간 결정될 것입니다.',
			'D', '01', 
			CURRENT_DATE , 'N' , 'Y' );
		
		
		
INSERT INTO PROJECT.REPLYBOARD
(RPY_SEQ, RPY_ID, RPY_REF, RPY_CONTENT, RPY_REGDATE, RPY_STATUS, RPY_CHOSEN)
VALUES('R11', 'gana@naver.com', 'Q22', '캐터피 잘 보고 갑니다. 저희병원에 오셔서 물고기 피부를 활용한 강아지 피부 치료 코스를 받아보시기 바랍니다.', SYSDATE , 'Y' , 'N' );

		
		
		
		
		
		
		