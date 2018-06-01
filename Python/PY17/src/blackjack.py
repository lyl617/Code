#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Time    : 12/05/2018 12:09 PM
# @Author  :
# @Site    : 
# @File    : blackjack.py
# @Software: PyCharm
from collections import defaultdict
import random
class blackjack(object):
    def __init__(self):
        self.deck_dict=defaultdict(None)#key value pair   "2D:2"
        self.is_game_over=False
        card_type=["S","H","D","C"]
        face_card=["J","Q","K","A"]
        for ct in card_type:
            for i in range(1,11):
                key_str=str(i)+ct
                self.deck_dict[key_str] = i
            for i in range(4):
                key_str = face_card[i] + ct
                if i==3:
                    self.deck_dict[key_str]=11
                else:
                    self.deck_dict[key_str]=10

    def main(self):
        player=list()
        computer=list()
        for i in range(2):#init player and computer
            player.append(self.deal_card())
            computer.append(self.deal_card())
        print("Player's card:")
        print(player)
        is_game_over=self.check_game_over(player,computer)
        is_player_over=False
        while (not is_player_over and not is_game_over):
            play_input=input("If want another card? YES(y) or NO(n)")
            if play_input=="NO" or play_input=="n":
                is_player_over=True
            else:
                player.append(self.deal_card())
                print("Player's card:")
                print(player)
                is_game_over=self.check_game_over(player,computer)
        sum_computer=0
        for card in computer:
            sum_computer+=self.deck_dict[card]
        while (sum_computer<16 and not is_game_over):
            add_card=self.deal_card()
            computer.append(add_card)
            sum_computer+=self.deck_dict[add_card]
            is_game_over=self.check_game_over(player,computer)
        if (not is_game_over):
            sum_player=0
            for card in player:
                sum_player+=self.deck_dict[card]
            if(sum_player>sum_computer):
                print("Player Wins!Score is %s"%str(sum_player))
            elif(sum_player<sum_computer):
                print("Computer Wins!Score is %s"%str(sum_computer))
            else:
                print("It is a tie.")
        is_game_again=input("You want to play again? YES(y) or NO(n)")
        if (is_game_again=="YES" or is_game_again=="y"):
            self.main()
        else:
            exit()
    def deal_card(self):
        return random.sample(list(self.deck_dict),1)[0]
    def check_game_over(self,player,computer):
        #len_deck=len(player)
        sum_player=0
        sum_computer=0
        for card in player:
            sum_player+=self.deck_dict[card]
        for card in computer:
             sum_computer+=self.deck_dict[card]
        if sum_player==21 and sum_computer<21:
            print("Blackjack!Player Wins!")
            return True
        elif sum_player<21 and sum_computer==21:
            print("Blackjack!Computer Wins!")
            return True
        elif sum_player==21 and sum_computer==21:
            print("Double Blackjack!Tie.")
            return True
        elif sum_player>21 and sum_computer<21:
            print("Player broke 21.Computer wins!")
            return True
        elif sum_player<21 and sum_computer>21:
            print("Computer broke 21.Player wins!")
            return True
        else:
            return False
if __name__ == "__main__":
    bj=blackjack()
    bj.main()
