# ///////////////////////////////////
# // CS4386 Semester B, 2021-2022
# // Assignment 1
# // Name: Kwok Ho Hin
# // Student ID: 56263267
# ///////////////////////////////////


class AIPlayer(object):
    def __init__(self, name, symbole, isAI=False):
        self.name = name
        self.symbole = symbole
        self.isAI = isAI
        self.score=0

    def stat(self):
        return self.name + " won " + str(self.won_games) + " games, " + str(self.draw_games) + " draw."

    def __str__(self):
        return self.name
    def get_isAI(self):
        return self.isAI
    def get_symbole(self):
        return self.symbole
    def get_score(self):
        return self.score
    def add_score(self,score):
        self.score+=score
     
    
    def empty_cells(self,state):
        cells = []

        for x, row in enumerate(state):
            for y, cell in enumerate(row):
                if cell is None:
                    cells.append([x, y])

        return cells
    def get_p_empty_cells(self, state:list) -> list:
        return [sorted([(x, y, cell) for y, cell in enumerate(row) if cell is None], key=lambda x: (x[0], x[1])) for x, row in enumerate(state)]

    def __chk_xy_istEmpty(self, chk_cell: tuple, state: list) -> bool:
        x = chk_cell[0]
        y = chk_cell[1]
        # print(chk_cell)

        if x > 0:
            if state[x-1][y] is not None:
                # print("x-1")
                return False
        if x > 1:
            if state[x-2][y] is not None:
                # print("x-2")
                return False

        if x < 4:
            if state[x+1][y] is not None:
                # print("x+1")
                return False
        if x < 3:
            if state[x+2][y] is not None:
                # print("x+2")
                return False

        if y > 0:
            if state[x][y-1] is not None:
                # print("y-1")
                return False
        if y > 1:
            if state[x][y-2] is not None:
                # print("y-2")
                return False

        if y < 4:
            if state[x][y+1] is not None:
                # print("y+1")
                return False

        if y < 3:
            if state[x][y+2] is not None:
                # print("y+2")
                return False

        return True


    def get_piror_empty_cells(self, empty_cells: list, state) -> list:

        piror_row = []
        non_emoty_cells: list = self.get_x_non_empty_cells(state)

        for x, row in enumerate(empty_cells):
            if len(row) > 0:
                dist_me, dist_opp = self.__count_row_dist(non_emoty_cells[x])
                for y, cell in enumerate(row):
                    normal_dist = 3
                    if not self.__chk_xy_istEmpty(cell, state):
                        normal_dist = 7
                    if dist_me + dist_opp == 4 :
                        normal_dist = 9
                    piror_row.append([cell[0], cell[1], dist_me-normal_dist])

        piror_row.sort(key=lambda x: x[2], reverse=True)
        return piror_row



    def get_x_non_empty_cells(self, state: list) -> list:
        return [sorted([(x, y, cell) for y, cell in enumerate(row) if cell is not None],key=lambda x: (x[0], x[1])) for x, row in enumerate(state)]

    def get_y_non_empty_cells(self, state: list) -> list:
        return [sorted([ (x, y, state[y][x]) for y in range(0, len(state[x])) if state[y][x] is not None], key=lambda x: (x[0], x[1])) for x in range(0, len(state))]

    def __count_row_dist(self, row: list) -> tuple:
        if len(row) == 0:
            return (0, 0)
        return (sum(1 for col in row if col[2] == 'O'), sum(1 for col in row if col[2] == 'X'))

    def __missing_y_index(self, row: list) -> int:
        indexs = [x[1] for x in row]
        return [x for x in range(0, 6) if x not in indexs]

    def __chk_conn(self, state: list, non_empty_cells: list, reverse:bool=False) -> list:
        q = []
        for x, row in enumerate(non_empty_cells):
            if len(row) == 5:
                dist_me, dist_opp = self.__count_row_dist(row)
                # print(dist_me, dist_opp)
                y = self.__missing_y_index(row)[0]
                if dist_opp >= dist_me:
                    #print("__missing_x_index", self.__missing_x_index(row))
                    q.insert(0, (x, y, 9))
                else:
                    #print("__missing_x_index", self.__missing_x_index(row))
                    q.append((x, y, 8))

            elif len(row) == 2:
                # if reverse:
                #     print("y-asix")
                # print("len(row)==2")
                start_pos_col = row[0]
                end_pos_col = row[-1]
                dist_me, dist_opp = self.__count_row_dist(row)
                prior = 5 if dist_opp >= dist_me else 4

                if end_pos_col[1] - start_pos_col[1] == 1:
                    # print("append_top", end_pos_col[1]+1)
                    q.append((x, end_pos_col[1]+1 if end_pos_col[1]+1 < 6 else start_pos_col[1]-1, prior))

                if end_pos_col[1] - start_pos_col[1] == 2:
                    # print("append_boot")
                    q.append((x, end_pos_col[1]-1, prior))
                # print("start:", start_pos_col, "end:", end_pos_col)
            elif len(row) > 2:

                if len(row) == 4 or len(row) == 6:
                    continue

                # print("len(row)>2")
                tmp_pos_col = None
                start_pos_col = row[0]
                dist_me, dist_opp = self.__count_row_dist(row)
                prior = 7 if dist_opp >= dist_me else 6
                print(start_pos_col)
                for y, col in enumerate(row[1::], start=1):
                    # print(col)
                    space_cal = start_pos_col[1] - col[1]
                    # print("space", space_cal, "-y:", y)

                    if space_cal == -2:
                        # print("==-2")
                        if state[x][col[1]-1] is None:
                            # print("col:", col)
                            if (col[1] == 5 or (col[1]+1 < 6 and state[x][col[1]+1] is None))  and \
                                    (start_pos_col[1] == 0 or (start_pos_col[1]-1 > -1 and state[x][start_pos_col[1]-1] is None)):
                                tmp_pos_col = (x, col[1]-1, prior)
                                # print("tmp:", tmp_pos_col)
                            else:
                                start_pos_col = col
                        else:
                            start_pos_col = col
                    elif space_cal == -1:
                        # print("==-y")

                        if start_pos_col[1]-2 >= 0 and state[x][start_pos_col[1]-2] is None:
                            tmp_pos_col = (x, start_pos_col[1]-1, prior)
                        elif col[1]+2 <=5 and state[x][col[1]+2] is None:
                            tmp_pos_col = (x, col[1]+1, prior)


                        #tmp_pos_col = (x, start_pos_col[1]-1 if start_pos_col[1]-1 > -1 else col[1]+1, prior)

                    elif space_cal >= -2:
                        # print(">=-2")
                        # print(col)
                        start_pos_col = col

                #     print("int:", tmp_pos_col)
                # print("end:", tmp_pos_col)
                if tmp_pos_col is not None:
                    q.append(tmp_pos_col)
        return q if not reverse else [(item[1], item[0], item[2]) for item in q]


    def get_q(self, state: list) -> list:
        non_empty_x_cells: list = self.get_x_non_empty_cells(state)
        #print("non-x:", non_empty_x_cells)
        non_empty_y_cells: list = self.get_y_non_empty_cells(state)
        # print("non-y:", non_empty_y_cells)
        q = self.__chk_conn(state, non_empty_y_cells, True) + self.__chk_conn(state, non_empty_x_cells)
        s: set = set()
        dups: list = []
        for pos in q:
            if pos not in s:
                s.add(pos)
            else:
                dups.append(pos)

        for dup in dups:
            while dup in q:
                q.remove(dup)
            q.insert(0, (dup[0], dup[1], dup[2]*2))

        test = self.get_piror_empty_cells(self.get_p_empty_cells(state), state)
        q += test
        q.sort(key=lambda x: x[2], reverse=True)
        # print("q", q)
        return q
        # print(non_empty_y_cells)

        # print("q:", q)




        # q.sort(key=lambda x: x[2], reverse=True)
        # return q



    def chk_pos(self, state: list, cell:tuple, player: str) -> bool:
        tmp_state = state.copy()
        tmp_state[cell[0]][cell[1]] = player
        empty_x_cells = self.get_x_non_empty_cells(tmp_state)
        return not ( len(state[cell[0]]) - len(self.get_x_non_empty_cells(tmp_state)[cell[0]]) == 1) \
                and not( len(state[cell[1]]) - len(self.get_y_non_empty_cells(tmp_state)[cell[1]]) == 1)


    def get_move(self,state, player):
        # print("param", state, player)
        # games = self.empty_cells(state)
        # self.get_q(state)
        #non_empty_cells = self.get_non_empty_cells(state)
        q = self.get_q(state)
        # print("q", q)
        for item in q:
            if state[item[0]][item[1]] is None and self.chk_pos(state, item, player):
                return (item[0], item[1])

        for item in q:
            if state[item[0]][item[1]] is None:
                return (item[0], item[1])
        # test = self.chk_connection(state)
        # return [test[0], test[1]]
        #print(games)
        # random_move=games[0]
        # return random_move # 2D array; return [row, col]
