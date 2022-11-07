package group6.graphics;

import group6.entities.Entity;
import group6.entities.block.Grass;
import group6.entities.block.Portal;
import group6.entities.block.Wall;
import group6.entities.block.Brick;
import group6.entities.item.FlameItem;
import group6.entities.item.SpeedItem;

import static group6.BombermanGame.*;

import java.io.*;
import java.util.StringTokenizer;

public class Map {

    public static void createMap(String map) {
        final File fileMap = new File(map);
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileMap));
            String line = br.readLine();
            String[] infoMap = line.split("\\s");
            try {
                _level = Integer.parseInt(infoMap[0]);
                _height = Integer.parseInt(infoMap[1]);
                _width = Integer.parseInt(infoMap[2]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

            pos = new int[_width][_height];
            posBomb = new int[_width][_height];
            for (int i = 0; i < _height; i++) {
                String lineObject = br.readLine();
                StringTokenizer tokenObject = new StringTokenizer(lineObject);

                for (int j = 0; j < _width; j++) {
                    int indexTile = Integer.parseInt(tokenObject.nextToken());
                    Entity object;

                    switch (indexTile) {
                        case 1:
                            object = new Portal(j, i, Sprite.brick.getFxImage());
                            break;
                        case 2:
                            object = new Wall(j, i, Sprite.wall.getFxImage());
                            break;
                        case 3:
                            object = new Brick(j, i, Sprite.brick.getFxImage());
                            break;
                        case 6:
                            object = new FlameItem(j, i, Sprite.brick.getFxImage());
                            break;
                        case 7:
                            object = new SpeedItem(j, i, Sprite.brick.getFxImage());
                            break;
                        default:
                            object = new Grass(j, i, Sprite.grass.getFxImage());
                    }
                    stillObjects.add(object);
                    pos[j][i] = indexTile;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
