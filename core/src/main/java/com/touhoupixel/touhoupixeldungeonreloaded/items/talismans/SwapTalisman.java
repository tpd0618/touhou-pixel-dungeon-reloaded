package com.touhoupixel.touhoupixeldungeonreloaded.items.talismans;

import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Char;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeonreloaded.items.scrolls.exotic.ScrollOfTeleportation;
import com.touhoupixel.touhoupixeldungeonreloaded.items.wands.WandOfWarding;
import com.touhoupixel.touhoupixeldungeonreloaded.levels.rooms.special.SentryRoom;
import com.touhoupixel.touhoupixeldungeonreloaded.mechanics.Ballistica;
import com.touhoupixel.touhoupixeldungeonreloaded.sprites.ItemSpriteSheet;

public class SwapTalisman extends Talisman {
    {
        image = ItemSpriteSheet.SWAP;
    }

    private static Ballistica throwPath;

    @Override
    public int throwPos(Hero user, int dst) {
        throwPath = new Ballistica(user.pos, dst, Ballistica.PROJECTILE);
        return throwPath.collisionPos;
    }

    @Override
    protected void onThrow(int cell) {

        Char ch = Actor.findChar(cell);

        if (ch != null && !(ch instanceof SentryRoom.Sentry) && !ch.properties().contains(Char.Property.MINIBOSS) && !ch.properties().contains(Char.Property.BOSS)) {
            cell = throwPath.path.get(throwPath.dist);
            throwPath = null;
            super.onThrow(cell);

            ch.move(curUser.pos);
            ch.sprite.move(cell, curUser.pos);

            ScrollOfTeleportation.teleportToLocation(curUser, cell);
        }
    }
}