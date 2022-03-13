def solution(clothes):
    answer = 0
    clist = {}
    
    for i in clothes:
        if i[1] in clist:
            clist[i[1]].append(i[0])
            continue 
        list = []
        list.append(i[0])
        clist[i[1]] = list
                  
    #print(clist)
    
    result = 1
    for key in clist.keys():
        result = result * (len(clist[key]) + 1)
    return result - 1
