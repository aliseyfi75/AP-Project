class GameState {

    private int spaceshipX;
    private int spaceshipY;

    GameState(int width, int height, Spaceship ss) {
        spaceshipX = (int) ((width - ss.getIcon().getIconWidth()) / 2 + 100 * Math.random() - 50);
        spaceshipY = height - ss.getIcon().getIconHeight() - 30;
    }

    GameState(Player player) {

    }

    GameState(GamePlay gp) {

    }

    int getSpaceshipX() {
        return spaceshipX;
    }

    int getSpaceshipY() {
        return spaceshipY;
    }
}
