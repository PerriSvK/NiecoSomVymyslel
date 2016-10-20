package sk.perri.spongia.veci;

import org.newdawn.slick.Graphics;
import sk.perri.spongia.utils.Constants;

import java.util.Vector;

public class Inventory
{
    private Vector<Entit> inside = new Vector<>();
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
        for(int i = 0; i < isIn; i++)
        {
            if(inside.get(i).typ == type)
            {
                res.add(inside.get(i));
                inside.remove(i);
            }

            if(res.size() >= count)
                break;
        }

        isIn -= res.size();

        stacky.stream().filter(s -> s.typ == type).forEach(s -> s.remove(count));

        return res;
    }

    public void drawStatus(Graphics g)
    {
        g.drawString("Invetntory: "+Integer.toString(isIn)+"/"+Integer.toString(capacity), Constants.WINDOW_WIDTH - 150, 10);
        for(int i = 0; i < stacky.size(); i++)
        {
            Stack s = stacky.get(i);
            g.drawString(Stack.typToString(s.typ)+": "+s.pocet, Constants.WINDOW_WIDTH - 90, 25+15*i);
        }
    }
}
