# ///////////////////////////////////
# // CS4386 Semester B, 2021-2022
# // Assignment 2
# // Name: Kwok Ho Hin
# // Student ID: 56263267
# ///////////////////////////////////

class Rtiel:
        
    def __init__(self, idx, priority, count) -> None:
        self.idx = idx
        self.priority = priority
        self.count = count
            
class AIPlayer():
    def __init__(self):
        self.name='AIPlayer'
        # self.drop_hist_cnt = [0]*9
        self.pre_cnt_state = []
        # self.fix_cnt = [0] * 9

    def update_state(self,gametable_receive_tiles,gametable_receive_cnt,player_cnt,player_cnt_p,player_cnt_c,oppo_cnt_p,oppo_cnt_c):
        self.gametable_receive_tiles=gametable_receive_tiles
        self.gametable_receive_cnt: list=gametable_receive_cnt
        self.player_cnt: list = player_cnt
        self.player_cnt_p = player_cnt_p
        self.player_cnt_c = player_cnt_c
        self.oppo_cnt_p=oppo_cnt_p
        self.oppo_cnt_c=oppo_cnt_c
        
    def think_pong(self):
        #return true or false
        # print("\ncall think_pong")
        # self.print_state()
        
        
        idx = self.find_ext_idx(self.gametable_receive_cnt)
        # print("idx", idx)
        chk_player_cnt = self.player_cnt.copy()
        
        pre_q_prior = self.predict_think(self.player_cnt ,priority_think=False)
        
        # print("pre_q_prior", pre_q_prior)
        chk_player_cnt[idx] += 1
        
        chk_q_prior = self.predict_think(chk_player_cnt.copy(), priority_think=False)
        # print("chk_q_prior", chk_q_prior)
        
        
        if self.chk_win(chk_player_cnt):
            # print("True")
            return True
        
        self.copy_pre_state()
        if chk_q_prior[idx] > pre_q_prior[idx]:
            # print("False")
            return False
        
        # print("True")
        return True
        # 1 6 777 88 9
        
    
    def find_ext_idx(self, gametable_receive_cnt: list) -> int:
        # print("cur", gametable_receive_cnt)
        # print(self.pre_cnt_state)
        
        if len(self.pre_cnt_state) == 0:
            for i in range(len(gametable_receive_cnt)):
                if gametable_receive_cnt[i] > 0:
                    return i
        
        for i in range(len(gametable_receive_cnt)):
            if gametable_receive_cnt[i] != self.pre_cnt_state[i]:
                return i
            
    def __get_db_idx(self, start, player_cnt: list) -> int:
        if start >= len(player_cnt):
            return -1
    
        for i in range(start, len(player_cnt)):
            if player_cnt[i] >= 2:
                return i
        return -1

    def __get_db_rec_idx(self, start: int, player_cnt: list) -> int:
        if start >= len(player_cnt) or start < 0:
            return -1
    
        for i in range(start, -1, -1):
            if player_cnt[i] >= 2:
                return i
        return -1
        
    def chk_win(self, player_cnt:list) -> bool:
        
        player_cnt = player_cnt.copy()
        
        if sum(self.player_cnt_c) > 0:
            for i in range(len(self.player_cnt_c)):
                for _ in range(self.player_cnt_c[i]):
                    player_cnt[i] += 1
                    player_cnt[i+1] += 1
                    player_cnt[i+2] += 1
        
        if sum(self.player_cnt_p) > 0:
            
            for i in range(len(self.player_cnt_p)):
                if self.player_cnt_p[i] > 0:
                    player_cnt[i] += 3
        
        
        trip_idx = self.__get_db_idx(0, player_cnt)
        # print(trip_idx)
        while trip_idx != -1 and trip_idx < len(player_cnt):
            
            for time in range(2):
                    
                player_cnt_cpy = player_cnt.copy()
                player_cnt_cpy[trip_idx] -= 2
                
                seq_fir = []
                seq_sec = []
                
                if time == 0:
                    
                    for i in range(len(player_cnt_cpy)):
                        
                        if player_cnt_cpy[i] >= 3:
                            
                            if len(seq_fir) == 0:
                                #print("find 3")
                                seq_fir = [i] * 3
                                player_cnt_cpy[i] -= 3
                            elif len(seq_sec) == 0:
                                #print("pk1", "cnt:", player_cnt_cpy[i])
                                return True
                        
                idx = -1
                
                for i in range(len(player_cnt_cpy)):
                    if player_cnt_cpy[i] > 0:
                        idx = i
                        break
                    
                while idx < len(player_cnt_cpy)-2:
                        #print("idx", idx)
                        if player_cnt_cpy[idx] == 0:
                            idx += 1
                            continue
                        
                        #print("idx-2+1", idx , "idx+1", idx+3, "res:",  player_cnt_cpy[idx: idx+3])
                        if 0 not in player_cnt_cpy[idx: idx+3]:
                            # print("0 not in ")
                            if len(seq_fir) == 0:
                                # print("chk1")
                                seq_fir = player_cnt_cpy[i: i+3]
                                # print("chk2")
                            else:
                                # print("else")
                                # print(player_cnt_cpy[idx: idx+3])
                                # print("pk2")
                                return True
                            player_cnt_cpy[idx] -= 1
                            player_cnt_cpy[idx+1] -= 1
                            player_cnt_cpy[idx+2] -= 1
                            # print("chk3")
                        else:
                            # print("idx+1")
                            idx +=1
                            
            trip_idx = self.__get_db_idx(trip_idx+1, player_cnt)
            # print("trip_idx", trip_idx)
        
        return False
            
        
        
    
    
    def think_chow(self):
        #return 1,2,3 or 0
        # print("\ncall think_chow")
        # self.print_state()
        # print("pre", self.pre_cnt_state)
        
        idx = self.find_ext_idx(self.gametable_receive_cnt)
        # print("idx", idx)
        
        if idx is None:
            return 0
        
        chk_player_cnt = self.player_cnt.copy()
        pre_q_prior = self.predict_think(self.player_cnt,priority_think=False)
        
        chk_player_cnt[idx] += 1
        
        chk_q_prior = self.predict_think(chk_player_cnt, priority_think=False)
        
        
        if chk_q_prior[idx] > pre_q_prior[idx]:
            # print("0")
            return 0
        
        # 1 1 0 1 1 
        
        pos_idx = self.__get_posible_idx(self.player_cnt, idx)
        if len(pos_idx) == 1:
            return pos_idx[0]
        
        for _ in range(len(pos_idx)):
            i = pos_idx.pop()
            if i == 1:
                
                if chk_q_prior[i+1] == 0 and chk_q_prior[i+2] == 0:
                    # print("1")
                    return 1
            
            elif i == 2:
                
                if chk_q_prior[i-1] == 0 and chk_q_prior[i+1] == 0:
                    # print("2")
                    return 2
            elif i == 3:
                
                if chk_q_prior[i-2] == 0 and chk_q_prior[i-1] == 0:
                    # print("3")
                    return 3
                
        return 0
    
    # def update_fix_cnt(self, idx: int, case: int) -> None:
    #     self.fix_cnt[idx] += 1
    #     if case == 1:
    #         self.fix_cnt[idx+1] += 1
    #         self.fix_cnt[idx+2] += 1
    #     elif case == 2:
    #         self.fix_cnt[idx-1] += 1
    #         self.fix_cnt[idx+1] += 1
    #     elif case == 3:
    #         self.fix_cnt[idx-2] += 1
    #         self.fix_cnt[idx-1] += 1
    
    def __get_posible_idx(self, player_cnt: list, idx: int) -> list:
        # 1 N N+1 N+2
        # 2 N-1 N N+1
        # 3 N-2 N-1 N
        
        result = []

            
        if idx+2 < len(player_cnt)-1:
            
            if player_cnt[idx+1] > 0 and player_cnt[idx+2] > 0:
                result.append(1)
            
            
        if idx+1 < len(player_cnt) and idx-1 > -1:
        
            if player_cnt[idx-1] > 0 and player_cnt[idx+1] > 0:
                result.append(2)
            
        if idx-2 > -1:
            if player_cnt[idx-2] > 0 and player_cnt[idx-1] > 0:
                result.append(3)
            
        return result
        
    
    
    def think(self):
        # print("\ncall think")
        # self.print_state()
        #print("\ntesting")
        
        predicts = self.predict_think(self.player_cnt)
        #print(t)
        self.copy_pre_state()
        self.pre_cnt_state[predicts[0]]+=1
        return predicts[0]
                        
                        
    def copy_pre_state(self):
        self.pre_cnt_state = self.gametable_receive_cnt.copy()

    def print_state(self):
        print("inf:")
        print(f"gametable_receive_tiles:{self.gametable_receive_tiles}")
        print(f"gametable_receive_cnt:{self.gametable_receive_cnt}")
        print(f"player_cnt:{self.player_cnt}")
        print(f"player_cnt_p:{self.player_cnt_p}")
        print(f"player_cnt_c:{self.player_cnt_c}")
        print(f"oppo_cnt_p:{self.oppo_cnt_p}")
        print(f"oppo_cnt_c:{self.oppo_cnt_c}")
        print()
        
    # def __convert_cnt(self, player_cnt:list) -> list:
    #     result = []
    #     for i, cnt in enumerate(player_cnt, start=1):
    #         result += [i] * cnt
    #     return result
    
    def __chk_conn(self, player_cnt: list, N: int):
        tmp_cnt = player_cnt.copy()
        chk_idx = len(player_cnt)-1
        while chk_idx > -1 + N-1:
            
            if tmp_cnt[chk_idx] == 0:
                chk_idx -= 1
                continue

            if 0 not in tmp_cnt[chk_idx-N+1: chk_idx+1]:
                for i in range(chk_idx-N+1, chk_idx+1):
                    tmp_cnt[i] -= 1
            else:
                chk_idx -=1
        for i in range(len(tmp_cnt)):
            if tmp_cnt[i] == N:
                tmp_cnt[i] -= N
        return tmp_cnt 
             
    
    def __normalize(self, pre_normal_cnt: list):
        
        orign_cnt = pre_normal_cnt.copy()
        converted_cnt = self.__chk_conn(pre_normal_cnt, 2)
        count_zero = 0
        for cnt in converted_cnt:
            if cnt == 0: 
                count_zero += 1
        if count_zero < len(orign_cnt):
            return converted_cnt
        else:
            return orign_cnt
        
     
    def find_not_seq_remove_first(self, player_cnt: list) -> list:
        
        tmp_cnt = player_cnt.copy()
        for i in range(len(tmp_cnt)):
            if tmp_cnt[i] == 3:
                tmp_cnt[i] = 0
            if tmp_cnt[i] == 4:
                tmp_cnt[i] = 1
                
        return self.__normalize(tmp_cnt)
    
    
    def find_not_seq_conn_first(self, player_cnt: list) -> list:
        
        tmp_cnt = self.__chk_conn(player_cnt, 3)
        for i in range(len(tmp_cnt)):
            if tmp_cnt[i] == 2:
                tmp_cnt[i] -= 2
            elif tmp_cnt[i] == 3 or tmp_cnt[i] == 4:
                tmp_cnt[i] -= 3
                
        return self.__normalize(tmp_cnt)
    
    # def __cal_left_tiles(self, player_cnt: list, drop_cnt: list, opp_ctn_p: list, opp_ctn_c: list):
        
    #     tiles = [4] * 9
    #     for i in range(player_cnt):
    #         tiles[i] -= player_cnt[i]
    #         tiles[i] -= drop_cnt[i]
    #         if opp_ctn_p[i] >= 1:
    #             for _ in range(opp_ctn_p[i]):
    #                 tiles[i] -= 1
    #         if opp_ctn_c[i] >= 1:
    #             tiles[i] -= 1
    #             tiles[i+1] -= 1
    #             tiles[i+2] -= 1
    #     return tiles
    
    # def __predict_opp_tiles(self, drop_cnt: list , opp_ctn_p: list, opp_ctn_c: list) -> list:
        
    #     opp_tiles = [0] * 9
    #     for i, ctn in enumerate(opp_ctn_c):
    #         if ctn > 0:
    #             opp_tiles[i]+=1
    #             opp_tiles[i+1]+=1
    #             opp_tiles[i+3]+=1
        
    #     for i, ctn in enumerate(opp_ctn_p):
    #         if ctn > 0:
    #             opp_tiles[i] += 3
        
    #     opp_drop_cnt = drop_cnt.copy()
    #     # [0,0,0,0,0,0,0,0,0]
        
    #     for i, cnt in enumerate(self.drop_hist_cnt):
    #         if cnt > 0:
    #             opp_drop_cnt[i] -= cnt
        
                
        
        
    
    def __priority_think(self, q: list) -> list:
        # print("__priority_think")
        # print("thinks", q)
        result = []
        for i, cnt in enumerate(q):
            prior = 1
            if cnt > 0:
                if self.gametable_receive_cnt[i] >= 2:
                    prior = 10
                elif self.oppo_cnt_p[i] >= 1:
                    prior = 8
                result.append(Rtiel(i, prior, cnt))
        

        result.sort(key=lambda x: (x.priority, x.count), reverse=True)
        # for r in result:
        #     print(r.idx)
        #     print(r.priority)
       
        return [x.idx for x in result]
                
    def predict_think(self, player_cnt: list, priority_think: bool=True):
        
        player_cnt_cpy = player_cnt.copy()
        fix_idx = []
        if sum(self.player_cnt_c) > 0:
            for i in range(len(self.player_cnt_c)):
                for _ in range(self.player_cnt_c[i]):
                    
                    # print("append")
                    player_cnt_cpy[i] += 1
                    player_cnt_cpy[i+1] += 1
                    player_cnt_cpy[i+2] += 1
                    fix_idx += [i, i+1, i+2]
            
        
        if sum(self.player_cnt_p) > 0:
            
            for i in range(len(self.player_cnt_p)):
                if self.player_cnt_p[i] > 0:
                    player_cnt_cpy[i] += 3
                    fix_idx.append(i)
        # print("fix_idx", fix_idx)
        
        # for i in range(len(self.fix_cnt)):
        #     if self.fix_cnt[i] > 0:
        #         player_cnt_cpy[i] -= self.fix_cnt[i]
        
        # print("player_cnt_cpy", player_cnt_cpy)
        db_idx = self.__get_db_rec_idx(len(self.player_cnt)-1, player_cnt_cpy.copy())
        
        q = []
        if db_idx != -1:
            
            while db_idx > -1:
                
                tmp_player_cnt_cpy = player_cnt_cpy.copy()
                tmp_player_cnt_cpy[db_idx] -= 2
                
                # idx = -1
                pre_data = self.__chk_conn(tmp_player_cnt_cpy, 3)
                out_data = self.__chk_conn(pre_data, 2)
                if pre_data == out_data:
                    q.append(out_data)
                else:
                    q.append(pre_data)
                    q.append(out_data)
                
                db_idx = self.__get_db_rec_idx(db_idx-1, tmp_player_cnt_cpy)
        q.append(self.find_not_seq_conn_first(player_cnt_cpy.copy()))
        q.append(self.find_not_seq_remove_first(player_cnt_cpy.copy()))
        
        q_prior = [0]*9
        
        
        for cnt in q:
            for i in range(len(cnt)):
                if cnt[i] > 0:
                    q_prior[i] += 1
        # print("q_prior", q_prior)
        if sum(q_prior) == 0:
            for i in range(len(self.player_cnt)):
                if player_cnt[i] > 0:
                    return [i]
        else:
                
            if not priority_think:
                return q_prior
                
            prior_thinks:list = self.__priority_think(q_prior)
            # print("prior_thinks", prior_thinks)
            if prior_thinks[0] in fix_idx:
                
                while len(prior_thinks) > 0 and prior_thinks[0] in fix_idx:
                    # print("prior_thinks chk")
                    count = 0
                    for chow in fix_idx:
                        if prior_thinks[0] == chow:
                            count+=1
                    if self.player_cnt[prior_thinks[0]] > count:
                        return prior_thinks
                    else:
                        prior_thinks.pop(0)
                
                for i in range(len(self.player_cnt)):
                    if player_cnt[i] > 0:
                        return [i]
            
            return prior_thinks
                

        
        
            
        
        
        