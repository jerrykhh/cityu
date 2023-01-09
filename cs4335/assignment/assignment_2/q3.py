class Fn:
      def __init__(self, cur_index, value):
        self.cur_index = cur_index
        self.value = value

def findMu(A:list) -> list:
  result = []
  if len(A) == 0:
    return [[]]
    
  
  for i in range(0, len(A)):
    q = []
    q.append(Fn(i,A[i]))
    

    for j in range(i+1, len(A)):
      num = A[j]
      if num % q[len(q)-1].value == 0:
        q.append(Fn(j, num))
    
    if len(q) > 1:
      result.append([fn.value for fn in q])

    __rec(q, A, result)
  return result

def __rec(q:list, A:list, result:list):
  
  for j in range(len(q)-1, -1, -1):
    if len(q) == 0:
      return
    
    new_q = []

    last_fn = q.pop()

    if len(q) == 0:
      return
    
    new_q = q

    for k in range(last_fn.cur_index+1, len(A)):
        if A[k] % new_q[len(new_q)-1].value == 0:
          new_q.append(Fn(k, A[k]))

    if len(new_q) > 1:
      result.append([fn.value for fn in new_q ]) 

    __rec(new_q, A, result)

def findLongestMuSub(A:list) -> list:
    answer = []
    mu_list = findMu(A)
    if len(mu_list) > 0:
        max = len(mu_list[0])
        answer.append(mu_list[0])

        for sub_list in mu_list[1::]:
            if (len(sub_list) > max):
                answer = [sub_list]
                max = len(sub_list)
            elif (len(sub_list) == max):
                answer.append(sub_list)
    return answer

def findMaxMuSub(A:list) -> list:
    answer = []
    mu_list = findMu(A)
    if len(mu_list) > 0:
        max_sum = sum(mu_list[0])
        answer.append(mu_list[0])

        for sub_list in mu_list[1::]:
            sub_sum = sum(sub_list)
            if(sub_sum > max_sum):
                answer = [sub_list]
                max_sum = sub_sum
            elif (sub_sum == max_sum):
                answer.append(sub_list)
    return answer


def main():
    # Q3(a)
    A = [1, 2, 3, 3, 4]
    print(findLongestMuSub(A))
    # Q3(b)
    A = [1, 2, 3, 3, 4, 5, 6, 7, 8, 15]
    print(findMaxMuSub(A))
    

if __name__ == "__main__":
  main()
