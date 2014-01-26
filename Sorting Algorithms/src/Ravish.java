/**
 * Created with IntelliJ IDEA.
 * User: Ravish
 * Date: 3/10/13
 * Time: 4:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class Ravish implements Comparable{

    Integer value;
    Boolean yos;

    public Ravish(Integer inti, Boolean bol){

        value = inti;
        yos = bol;



    }

    public int compareTo(Object obj)
    {
      //  if (obj instanceof Ravish){

            Ravish Chawla = (Ravish)obj;

            return (value.compareTo(Chawla.value));
      //  }

      //  return 0;
    }

    public String toString(){

        return "<"+value +" " + yos +">";
    }


}
