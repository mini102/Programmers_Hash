def solution(genres, plays):
    answer = []
    map = {}
    #map부터 만들기
    for index,i in enumerate(genres):
        if i in map.keys():
            map[i][0] += plays[index]
            map[i][1].append((plays[index],index))
            continue
        map[i] = [plays[index],[(plays[index],index)]]
    
    #print(map)
    
    best = sorted(map.values(), reverse = True)
    #print(best)
    
    numG = len(map.keys())
    
    for i in best:
        for j in i[1:]:
            many = sorted(j,reverse=True)
            list = []
            for index,g in enumerate(many):
                if g[0] in list:
                    if many[index-1][1]>g[1]:
                        temp = many[index]
                        many[index] = many[index-1]
                        many[index-1] = temp
                list.append(g[0])
            #print(many)
            for k in range(len(many)):
                if k<2 and many[k][1] != None:
                    #print(many[k][1])
                    answer.append(many[k][1])
                
    return answer
