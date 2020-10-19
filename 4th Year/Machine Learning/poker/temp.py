
'''
    pattern = r'id=([0-9]+)'
    display_id = re.search(pattern, pokerWindows[0]).group(1)

APP_NAME = "Squanchy" #this can be just a part of apps window name
QUALITY = 25  # change recoding quality 0-25, 0 - perfect and large file , 25 -poor quality and small file
PRESET = "medium"  # change this to achieve smaller file sizes (slower -> smaller file size) Possible values are: ultrafast, superfast, veryfast, faster, fast, medium, slow, slower, veryslow, placebo
SCALE_W = 640  #
SCALE_H = 480
CROP_W = 640
CROP_H = 225
CROP_X = 0
CROP_Y = 180
FPS = 30
CODEC = "libx264"  # try one of the following libx264_nvenc
OUT_FILENAME = APP_NAME + "_recording_" + time.strftime("%Y%m%d-%H%M%S")


pattern = r'id=([0-9]+)'
pokerWindows = check_output(["GetWindowID", "PokerStarsEU", "--list"]).decode('UTF-8').splitlines()
pokerWindows = [window for window in pokerWindows if "Logged In as" in window]

windowIds = []
for window in pokerWindows:
  windowIds.append(re.search(pattern,window).group(1))

for id in windowIds:
  call(["screencapture", "-l", id, "{}.jpg".format(id)])

'''
'''
def get_current_diplay_id():
    wcommand = Popen(['w', '-hs'], stdout=PIPE, stderr=STDOUT)
    stdout, stderr = wcommand.communicate()
    return stdout.decode('utf-8').split()[2]


def get_window_id(name):
    xwininfo = Popen(['xwininfo', '-tree', '-root'], stdout=PIPE, stderr=STDOUT)
    stdout, stderr = xwininfo.communicate()
    for line in stdout.decode('utf-8').splitlines():
        l = line.find(name)
        if l != -1:
            return line.split()[0]

def get_window_attrs(window_id):
    xwininfo_id = Popen(['xwininfo', '-id', window_id], stdout=PIPE, stderr=STDOUT)
    stdout, stderr = xwininfo_id.communicate()
    w, h, x, y = ("",) * 4
    for line in stdout.decode('utf-8').splitlines():
        if line.find("Width") != -1:
            w = line.split()[1]
        if line.find("Height") != -1:
            h = line.split()[1]
        if line.find("Absolute upper-left X") != -1:
            x = line.split()[3]
        if line.find("Absolute upper-left Y") != -1:
            y = line.split()[3]
    return w, h, x, y
'''
