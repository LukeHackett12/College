import re
from subprocess import call, check_output, Popen, PIPE,  STDOUT
import time
import ffmpeg # pip install ffmpeg-python
import pyautogui
import numpy as np
from pyautogui import position

def getWindowSize():
    pokerWindows = check_output(["GetWindowID", "PokerStarsEU", "--list"]).decode('UTF-8').splitlines()
    pokerWindows = [window for window in pokerWindows if "Logged In as" in window]

    pattern = r'size=([0-9]+)x([0-9]+)'
    sizes = [[re.search(pattern, pokerWindow).group(1),re.search(pattern, pokerWindow).group(2)] for pokerWindow in pokerWindows]
    size = [int(sizes[0][0])*2, int(sizes[0][1])*2] # Scale for pyautogui, doubles for some reason
    return size # for simplictity assume all the poker windows are around the same size

def getWindowLocation():
    instance = pyautogui.locateOnScreen('/Users/lukehackett/Documents/College/4th Year/Machine Learning/poker/star.png', grayscale=True, confidence=.85)
    if instance != None:
        return [int(instance.left), int(instance.top)]
    return [0,0]

def gameLoop(size, windowLocation):
    while True:
        print(size)
        print(windowLocation)

gameWindowSize = getWindowSize()
windowLocation = getWindowLocation()

gameLoop(gameWindowSize, windowLocation)

pyautogui.screenshot('my_screenshot.png', region=(x,y,int(sizes[0])*2,int(sizes[1])*2))
