
/**

 */
public class node
{
    private char data;
    private node parent;
    private node child;
    private node next;
    private node previous;
    private boolean end;
    private boolean flag;
    //constructor
    public node(char data)
    {
        this.data = data;
        this.parent = null;
        this.child = null;
        this.next = null;
        this.previous = null;
        this.end = false;
        this.flag = false;
    }
    public char getData()
    {
        return this.data;
    }
    public void setData(char value)
    {
        this.data = value;
    }
     public node getParent()
    {
        return this.parent;
    }
    public void setParent(node newParent)
    {
        this.parent = newParent;
    }
    public node getChild()
    {
        return this.child;
    }
    public void setChild(node newChild)
    {
        this.child = newChild;
    }
    public node getNext()
    {
        return this.next;
    }
    public void setNext(node value)
    {
        this.next = value;
    }
    public node getPrevious()
    {
        return this.previous;
    }
    public void setPrevious(node newPrevious)
    {
        this.previous = newPrevious;
    }
    public boolean getEnd()
    {
        return this.end;
    }
    public void setEnd()
    {
        this.end = true;
    }
    public void setFlag()
    {
        this.flag = true;
    }
    public boolean getFlag()
    {
        return this.flag;
    }
}
