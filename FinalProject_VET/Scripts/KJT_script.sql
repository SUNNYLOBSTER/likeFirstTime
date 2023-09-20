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
    
   
   
   
   
   