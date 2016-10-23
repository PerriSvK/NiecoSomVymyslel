package sk.perri.spongia.veci;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import sk.perri.spongia.utils.Constants;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import java.util.stream.Collectors;

public class Inventory
{
    private Vector<Vec> inside = new Vector<>();
    private int isIn = 0;
    private int capacity;
    private Vector<Stack> stacky = new Vector<>();

    public Inventory(int capacity)
    {
        this.capacity = capacity;
    }

    public boolean add(Vec entit)
    {
        if(isIn == capacity)
            return false;


        Constants.print("INV, entit typ:", entit.getTyp(), "stacks no:", stacky.size());
        if(isEmpty())
        {
            stacky.add(new Stack(entit.getTyp(), 1));
        }
        else
        {
            boolean fo = false;
            for(int i = 0; i < stacky.size(); i++)
            {
                Stack s = stacky.get(i);
                if(s.typ == entit.getTyp())
                {
                    s.add(1);
                    fo = true;
                    break;
                }
            }

            if(!fo)
            {
                stacky.add(new Stack(entit.getTyp(), 1));
                Constants.print("create new stack:", entit.getTyp());
            }
        }

        inside.add(entit);
        isIn++;
        return true;
    }

    public int contains(int itemTyp)
    {
        for(Stack s : stacky)
        {
            if(s.typ == itemTyp)
                return s.pocet;
        }

        return 0;
    }

    public Set<Integer> contains(Set<Integer> itemTyp)
    {
        return itemTyp.stream().map(this::contains).collect(Collectors.toSet());
    }

    public boolean isFull()
    {
        return isIn >= capacity;
    }

    public boolean isEmpty()
    {
        return isIn <= 0;
    }

    public void clear()
    {
        isIn = 0;
        inside.clear();
        stacky.clear();
    }

    public Entit remove(int index)
    {
        isIn--;
        Entit remE = inside.remove(index);
        stacky.stream().filter(s -> s.typ == remE.typ).forEach(s -> s.remove(1));

        return remE;
    }

    public Vector<Entit> remove(int type, int count)
    {
        Vector<Entit> res = new Vector<>();
        for(int i = isIn - 1 ; i >= 0; i--)
        {
            if(inside.get(i).getTyp() == type)
            {
                res.add(inside.get(i));
            }

            if(res.size() >= count)
                break;
        }

        inside.removeAll(res);
        isIn = inside.size();

        stacky.stream().filter(s -> s.typ == type).forEach(s -> s.remove(count));
        for(int i = 0; i < stacky.size(); i++)
        {
            if(stacky.get(i).pocet <= 0)
                stacky.remove(i);
        }
        return res;
    }

    public void drawStatus(Graphics g)
    {
        g.drawString("Invetntory: "+Integer.toString(isIn)+"/"+Integer.toString(capacity), Constants.WINDOW_WIDTH - 160, 10);
        for(int i = 0; i < stacky.size(); i++)
        {
            Stack s = stacky.get(i);
            g.drawString(Stack.typToString(s.typ)+": "+s.pocet, Constants.WINDOW_WIDTH - 90, 25+15*i);
        }
    }
}
