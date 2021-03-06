import random
import copy


class Rover:

    def __init__(self, coordinates, planet):
        """
        Initialises the rover
        """
        self.charge = 100  # charge of rover
        self.num_explored = 0  # number of explored tile
        self.coordinates = coordinates  # coordinates of rover
        self.planet = planet
        self.explored_section = 0  # percentage of explored tile
        self.isfinish = False
        self.ismove=False
        self.scan_tile=list()
        self.planet.tiles[self.planet.height-1-self.coordinates[1]][self.coordinates[0]].isexplored=True

    def move(self, direction, cycles):
        """
        Moves the rover on the planet
        """
        self.isstart = False
        int_cycles = int(cycles)
        x_coor = int(self.coordinates[0])
        y_coor = int(self.coordinates[1])
        tiles = self.planet.tiles
        height = int(self.planet.height)
        width = int(self.planet.width)
        total_tiles = height * width
        self.ismove=True
        if direction == "N":
            for i in range(int_cycles):
                isCanNext = False
                if self.charge > 0 and not self.isfinish:
                    now_tile = tiles[height - 1 - y_coor][x_coor]
                    now_elevation = now_tile.elevation
                    #now_index = random.randint(0, len(now_elevation) - 1)
                    if now_tile.isexplored == False:
                        now_tile.isexplored = True
                    if y_coor >= self.planet.height - 1:
                        y_coor = 0
                    else:
                        y_coor += 1
                    next_tile = tiles[height - 1 - y_coor][x_coor]
                    next_elevation = next_tile.elevation

                    for now_e in now_elevation:
                        for next_e in next_elevation:
                            if now_e==next_e:
                                isCanNext=True
                    #next_index = random.randint(0, len(next_elevation) - 1)
                    #if now_elevation[now_index] == next_elevation[next_index]:
                    if isCanNext:
                        self.coordinates[0] = x_coor
                        self.coordinates[1] = y_coor
                        if next_tile.is_shaded():
                            self.charge -= 1
                        if not next_tile.isexplored:
                            # if self.num_explored<total_tiles:
                            #self.num_explored += 1
                            next_tile.isexplored = True
                    else:
                        break
            # print("Now coordinates:%s,%s" % (str(self.coordinates[0]), str(self.coordinates[1])))

        elif direction == "E":
            for i in range(int_cycles):
                isCanNext=False
                if self.charge > 0 and not self.isfinish:
                    now_tile = tiles[height - 1 - y_coor][x_coor]
                    now_elevation = now_tile.elevation
                    #now_index = random.randint(0, len(now_elevation) - 1)
                    if now_tile.isexplored == False:
                        now_tile.isexplored = True
                    # now_elevation=temp_elevation[now_index]
                    if x_coor >= self.planet.width - 1:
                        x_coor = 0
                    else:
                        x_coor += 1
                    next_tile = tiles[height - 1 - y_coor][x_coor]
                    next_elevation = next_tile.elevation
                    for now_e in now_elevation:
                        for next_e in next_elevation:
                            if now_e==next_e:
                                isCanNext=True

                    #next_index = random.randint(0, len(next_elevation) - 1)
                    #if now_elevation[now_index] == next_elevation[next_index]:
                    if isCanNext:
                        self.coordinates[0] = x_coor
                        self.coordinates[1] = y_coor
                        if next_tile.is_shaded():
                            self.charge -= 1
                        if not next_tile.isexplored:
                            #self.num_explored += 1
                            next_tile.isexplored = True
                    else:
                        break
            # print("Now coordinates:%s,%s" % (str(self.coordinates[0]), str(self.coordinates[1])))
        elif direction == "S":
            for i in range(int_cycles):
                isCanNext = False
                if self.charge > 0 and not self.isfinish:
                    now_tile = tiles[height - 1 - y_coor][x_coor]
                    now_elevation = now_tile.elevation
                    #now_index = random.randint(0, len(now_elevation) - 1)
                    if now_tile.isexplored == False:
                        now_tile.isexplored = True
                    if y_coor <= 0:
                        y_coor = self.planet.height - 1
                    else:
                        y_coor -= 1
                    next_tile = tiles[height - 1 - y_coor][x_coor]
                    next_elevation = next_tile.elevation

                    for now_e in now_elevation:
                        for next_e in next_elevation:
                            if now_e==next_e:
                                isCanNext=True
                    #next_index = random.randint(0, len(next_elevation) - 1)
                    #if now_elevation[now_index] == next_elevation[next_index]:
                    if isCanNext:
                        self.coordinates[0] = x_coor
                        self.coordinates[1] = y_coor
                        if next_tile.is_shaded():
                            self.charge -= 1
                        if not next_tile.isexplored:
                            #self.num_explored += 1
                            next_tile.isexplored = True
                    else:
                        break
            # print("Now coordinates:%s,%s" % (str(self.coordinates[0]), str(self.coordinates[1])))
        elif direction == "W":
            for i in range(int_cycles):
                isCanNext=False
                if self.charge > 0 and not self.isfinish:
                    now_tile = tiles[height - 1 - y_coor][x_coor]
                    now_elevation = now_tile.elevation
                    #now_index = random.randint(0, len(now_elevation) - 1)
                    if now_tile.isexplored == False:
                        now_tile.isexplored = True
                    if x_coor <= 0:
                        x_coor = self.planet.width - 1
                    else:
                        x_coor -= 1
                    next_tile = tiles[height - 1 - y_coor][x_coor]
                    next_elevation = next_tile.elevation
                    #next_index = random.randint(0, len(next_elevation) - 1)

                    for now_e in now_elevation:
                        for next_e in next_elevation:
                            if now_e==next_e:
                                isCanNext=True
                    #if now_elevation[now_index] == next_elevation[next_index]:
                    if isCanNext:
                        self.coordinates[0] = x_coor
                        self.coordinates[1] = y_coor
                        if next_tile.is_shaded():
                            self.charge -= 1
                        if not next_tile.isexplored:
                            next_tile.isexplored = True

                            #self.num_explored += 1
                    else:
                        break
            # print("Now coordinates:%s,%s" % (str(self.coordinates[0]), str(self.coordinates[1])))
        else:
            print("Please input right direction!")

    def wait(self, cycles):
        """
        The rover will wait for the specified cycles
        """
        x_coor = self.coordinates[0]
        y_coor = self.coordinates[1]
        temp_tile = self.planet.tiles[self.planet.height - 1 - y_coor][x_coor]
        if temp_tile.type != "shaded":
            for i in range(int(cycles)):
                if self.charge < 100:
                    self.charge += 1
                else:
                    break

    def scan(self, type):
        planet_width = int(self.planet.width)
        planet_height = int(self.planet.height)
        mid_width = int(planet_width // 2)
        mid_height = int(planet_height // 2)
        tiles = self.transfer(planet_width, planet_height, mid_width, mid_height)
        scan_tile = list()
        left = 0
        right = 0
        top = 0
        bottom = 0

        # if planet_height%2==0:
        # mid_height-=1
        if planet_width >= 5 and planet_height >= 5:
            left = mid_width - 2
            right = mid_width + 2
            top = mid_height + 2
            bottom = mid_height - 2
        elif planet_width < 5 and planet_height >= 5:
            right = planet_width - 1
            top = mid_height + 2
            bottom = mid_height - 2
        elif planet_width >= 5 and planet_height < 5:
            left = mid_width - 2
            right = mid_width + 2
            top = planet_height - 1
        else:
            right = planet_width - 1
            top = planet_height - 1
        ##get scan tiles from whole tiles
        for i in range(bottom, top + 1):
            if left > 0:
                scan_tile.append(tiles[i][left:right + 1])
            else:
                scan_tile.append(tiles[i][:right + 1])
        ##scan_tile is 5*5 tiles if height>5 and width>5
        mid_scan_height = int(len(scan_tile) // 2)
        mid_scan_width = int(len(scan_tile[0]) // 2)
        if type == "shade":
            for tile in scan_tile:
                for t in tile:
                    if t.type == "shaded":
                        t.set_occupant("#")
                    else:
                        t.set_occupant(" ")
        elif type == "elevation":
            rover_coor = scan_tile[mid_scan_height][mid_scan_width].elevation
            if len(rover_coor) == 1:  # if number of rover's elevation is 1
                for tile in scan_tile:

                    for t in tile:
                        temp_coor = t.elevation
                        if len(temp_coor) == 1:  # if number of tile's elevation is 1
                            if temp_coor[0] > rover_coor[0]:
                                t.set_occupant("+")
                            elif temp_coor[0] < rover_coor[0]:
                                t.set_occupant("-")
                            else:
                                t.set_occupant(" ")
                        else:  # if number of tile's elevation is 2
                            if rover_coor[0] < temp_coor[0]:
                                t.set_occupant("/")
                            elif rover_coor[0] > temp_coor[1]:
                                t.set_occupant("\\")

            else:  # if number of rover's elevation is 2
                temp_index = random.randint(0, 1)
                for tile in scan_tile:
                    for t in tile:
                        temp_coor = t.elevation
                        if len(temp_coor) == 1:  # if number of tile's elevation is 1
                            if temp_coor[0] > rover_coor[temp_index]:
                                t.set_occupant("+")
                            elif temp_coor[0] < rover_coor[temp_index]:
                                t.set_occupant("-")
                            else:
                                t.set_occupant(" ")
                        else:  # if number of tile's elevation is 2
                            if rover_coor[temp_index] < temp_coor[0]:
                                t.set_occupant("/")
                            elif rover_coor[temp_index] > temp_coor[1]:
                                t.set_occupant("\\")

        else:
            print("Cannot perform this command")
        scan_tile[mid_scan_height][mid_scan_width].set_occupant("H")
        self.explored_tile(scan_tile)
        self.scan_print(scan_tile)
        # if self.num_explored == 0:
        #     self.num_explored = planet_height * planet_width
        # if self.num_explored > planet_height*planet_width:
        #     self.num_explored -= planet_height*planet_width
        #     self.num_explored += 1
    def explored_tile(self,tiles):
        len_height=len(tiles)
        mid_height=len_height//2
        len_width=len(tiles[0])
        mid_width=len_width//2
        x_coor=self.coordinates[0]-mid_width
        if x_coor<0:
            x_coor=self.planet.width+x_coor
        y_coor=self.coordinates[1]+mid_height
        if y_coor>self.planet.height-1:
            y_coor=y_coor-self.planet.height
        temp_x=x_coor
        for i in range(len_height):
            if y_coor<0:
                y_coor=self.planet.height-1
            for j in range(len_width):
                if x_coor>self.planet.width-1:
                    x_coor=0
                self.planet.tiles[self.planet.height-1-y_coor][x_coor].isexplored=True
                self.planet.tiles[self.planet.height - 1 - y_coor][x_coor].set_occupant(tiles[i][j].get_occupant())
                x_coor+=1
            y_coor-=1
            x_coor=temp_x


    def scan_print(self, tiles):
        """
        print scan tiles
        :param tiles:
        :return:
        """
        print()
        for t in tiles:
            temp_list = list()
            for _t in t:
                temp_list.append(_t.get_occupant())
            temp_str = '|'.join(temp_list)
            print("|%s|" % temp_str)
        print()

    def transfer(self, planet_width, planet_height, mid_width, mid_height):
        temp_tiles = copy.deepcopy(self.planet.tiles)
        x_coor = int(self.coordinates[0])
        y_coor = int(self.coordinates[1])
        ##transfer y coor of rover to middle
        if y_coor > mid_height:
            if planet_height%2==0:
                transefer_num=y_coor-mid_height+1
            else:
                transefer_num=y_coor-mid_height
            for i in range(transefer_num):
                former_tile = temp_tiles[0]
                for i in range(1, planet_height):
                    temp_tile = temp_tiles[i]
                    temp_tiles[i] = former_tile
                    former_tile = temp_tile
                temp_tiles[0] = former_tile
        elif y_coor < mid_height:
            if planet_height%2==0:
                transefer_num=mid_height-y_coor-1
            else:
                transefer_num=mid_height-y_coor
            for i in range(transefer_num):
                back_tile = temp_tiles[0]
                for i in range(1, planet_height):
                    temp_tiles[i - 1] = temp_tiles[i]
                temp_tiles[planet_height - 1] = back_tile
        # else:
        #     if self.planet.height%2==0:
        #         print("n")
        #         former_tile = temp_tiles[0]
        #         for i in range(1, planet_height):
        #             temp_tile = temp_tiles[i]
        #             temp_tiles[i] = former_tile
        #             former_tile = temp_tile
        #         temp_tiles[0] = former_tile

        ##transfer x coor of rover to middle
        if x_coor > mid_width:
            for i in range(x_coor - mid_width):
                for j in range(planet_height):
                    former_tile = temp_tiles[j][0]
                    for z in range(1, planet_width):
                        temp_tiles[j][z - 1] = temp_tiles[j][z]
                    temp_tiles[j][planet_width - 1] = former_tile
        elif x_coor < mid_width:
            for i in range(mid_width - x_coor):
                for j in range(planet_height):
                    back_tile = temp_tiles[j][planet_width - 1]
                    for z in range(planet_width):
                        temp_tile = temp_tiles[j][z]
                        temp_tiles[j][z] = back_tile
                        back_tile = temp_tile
        return temp_tiles
    def sum_explored(self):
        # if self.isstart:
        #     self.num_explored=1
        # elif not self.ismove:
        #     self.num_explored=self.planet.width*self.planet.height
        #else:
            tiles = self.planet.tiles
            for tile in tiles:
                for t in tile:
                    if t.isexplored==True and not t in self.scan_tile:
                        self.num_explored+=1
                        self.scan_tile.append(t)
    def stats(self):
        width = int(self.planet.width)
        height = int(self.planet.height)
        total_tail = width * height

        # if not self.isstats:
        self.sum_explored()
        # self.isstats = True
        self.explored_section = int(self.num_explored / total_tail * 100)
        print("\nExplored: %.0f%%" % self.explored_section)
        print("Battery: %s/100\n" % str(self.charge))

    def finish(self):
        width = int(self.planet.width)
        height = int(self.planet.height)
        total_tail = width * height

        self.sum_explored()
        print("\nYou explored %.0f%% of %s\n" % (int(self.num_explored / total_tail * 100), self.planet.name))
        self.isfinish = True


