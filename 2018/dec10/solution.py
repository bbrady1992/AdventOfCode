from sys import argv

class Point:
    def __init__(self, x, y, vx, vy):
        self.x = x
        self.y = y
        self.vx = vx
        self.vy = vy
        self.position = (self.x, self.y)
        self.velocity = (self.vx, self.vy)

    def move(self):
        self.x += self.vx
        self.y += self.vy
        self.position = (self.x, self.y)

class Canvas:
    def __init__(self, points_file_name, output_file):
        self.points = []
        self.pointPositions = set()
        self.out = open(output_file, 'a+')
        self.bp = {"min_x": 1000000, "max_x": -1000000, "min_y": 1000000, "max_y": -1000000}

        min_x = 100000
        max_x = -100000
        min_y = 100000
        max_y = -100000
        with open(points_file_name) as f:
            for line in f:
                x, y, vx, vy = self.parse_point_line(line)
                new_point = Point(x,  y, vx, vy)
                self.points.append(new_point)
                self.pointPositions.add(new_point.position)
                min_x = new_point.x if new_point.x < min_x else min_x
                max_x = new_point.x if new_point.x > max_x else max_x
                min_y = new_point.y if new_point.y < min_y else min_y
                max_y = new_point.y if new_point.y > max_y else max_y
        self.update_bounds(min_x, max_x, min_y, max_y)

    def ready_to_print(self):
        return self.bp["max_y"] - self.bp["min_y"] < 10

    def print(self):
        char_buffer = ""
        for y in range(self.bp["min_y"], self.bp["max_y"] + 1):
            for x in range(self.bp["min_x"], self.bp["max_x"] + 1):
                if (x, y) in self.pointPositions:
                    print('#', end = '')
                else:
                    print('.', end = '')
            print('\n', end = '')
        self.out.write('\n\n')

    def update_bounds(self, min_x, max_x, min_y, max_y):
        self.bp["min_x"] = min_x
        self.bp["max_x"] = max_x
        self.bp["min_y"] = min_y
        self.bp["max_y"] = max_y

    def update(self):
        self.pointPositions = set()
        min_x = 100000
        max_x = -100000
        min_y = 100000
        max_y = -100000
        for point in self.points:
            point.move()
            self.pointPositions.add(point.position)
            min_x = point.x if point.x < min_x else min_x
            max_x = point.x if point.x > max_x else max_x
            min_y = point.y if point.y < min_y else min_y
            max_y = point.y if point.y > max_y else max_y
        self.update_bounds(min_x, max_x, min_y, max_y)

    def run(self):
        elapsed_seconds = 0
        while not self.ready_to_print():
            canvas.update()
            elapsed_seconds += 1
        canvas.print()
        print("Took {} seconds to decipher the message".format(elapsed_seconds))

    @staticmethod
    def parse_point_line(line):
        words = line.split('<')
        position = words[1].split(',')
        x = int(position[0])
        y = int(position[1].split('>')[0])
        velocity = words[2].split(',')
        vx = int(velocity[0])
        vy = int(velocity[1].split('>')[0])
        return x, y, vx, vy

if __name__ == "__main__":
    print("Initializing canvas...")
    canvas = Canvas(argv[1], "out.txt")
    canvas.run()
