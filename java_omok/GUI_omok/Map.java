package ø¿∏Ò∆«;

import java.awt.Graphics;

public class Map {
	private short[][] map;
	public Map(MapSize ms) {
		map = new short[ms.getSize()][];
		for(int i = 0; i<map.length; i++) {
			map[i] = new short[ms.getSize()];
		}
	}
}
