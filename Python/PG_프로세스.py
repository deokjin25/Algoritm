def solution(priorities, location):
    #우선 순위 개수
    prior_dict = dict()
    for priority in priorities :
        if priority in prior_dict :
            prior_dict[priority] += 1
        else :
            prior_dict[priority] = 1
    
    
    # location 맵핑 리스트
    zip_list = list(zip(priorities, range(0, len(priorities))))
    
    # 최대 우선순위 지정
    sort_priorty = sorted(prior_dict, reverse=True)
    m_c=0
    max_priorty = sort_priorty[m_c]
    
    #큐 돌리면서 찾기
    process = 0
    while True :
        cur, loc = zip_list.pop(0)
        
        #현재 꺼낸 값이 최대 우선순위 일 경우 + 찾던 위치인 경우 -> 종료
        if cur == max_priorty and loc == location :
            process += 1
            break
            
        #현재 꺼낸 값이 최대 우선순위이기만 한 경우 -> 프로세스 횟수 증가
        if cur == max_priorty :
            process += 1
            prior_dict[cur] -= 1
            # 최대 우선 순위가 다 한 경우, 재 배정
            if prior_dict[cur] == 0 :
                m_c += 1
                max_priorty = sort_priorty[m_c]
                    
        #최대 우선 순위가 아닌 경우 -> 제일 뒤로 다시 삽입
        if cur != max_priorty :
            zip_list.append((cur, loc))
        
        
    
    return process