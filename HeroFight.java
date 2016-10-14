import java.util.Random;
import java.io.*;
public class HeroFight{
	@SuppressWarnings("unchecked")
	public static void main(String[] args)throws ClassNotFoundException{
		System.out.println("在这里输入两个人物进行PK,以英文逗号分隔：");
		System.out.println("[mage,hunter,warrior,rogue,knight,hackbutter,priest,druid,shaman]");
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		Class<Hero> c1;
		Class<Hero> c2;
		try {
			String temp=br.readLine();
			String[] str=temp.split(",");

			c1=(Class<Hero>)Class.forName(str[0].toUpperCase());
			c2=(Class<Hero>)Class.forName(str[1].toUpperCase());
		} catch(Exception e){
			System.out.println("输入格式有误，按默认PK");
			c1=(Class<Hero>)Class.forName("MAGE");
			c2=(Class<Hero>)Class.forName("HUNTER");
		}
		try{
			Hero h1=c1.newInstance();
			Hero h2=c2.newInstance();
			long time=System.currentTimeMillis();
			double nextTime1=(double)(time + h1.coldTime*1000); //
			double nextTime2=(double)(time + h2.coldTime*1000); //发动攻击的时间
			System.out.println("---游戏开始---");
			while(true){
				if(h1.blood<=0){
					System.out.println(h2.getClass().getSimpleName().toLowerCase()+"胜出!");
					System.exit(0);
				}
				if(h1.blood<=0){
					System.out.println(h1.getClass().getSimpleName().toLowerCase()+"胜出!");
					System.exit(0);
				}
				long currenTime=System.currentTimeMillis();

        		if (nextTime1<currenTime) { //时间到则发动攻击
        			h1.hit(h2);
        			nextTime1=nextTime1+h1.coldTime*1000+h1.waitTime*1000; //下次攻击时间=冷却时间+被晕眩时间
        			h1.waitTime=0; //回合结束，重置被晕眩时间为0
        		}
        		if (nextTime2<currenTime) {
        			h2.hit(h1);
        			nextTime2=nextTime2+h2.coldTime*1000+h2.waitTime*1000;
        			h2.waitTime = 0;
        		}
        	}
        }catch(ClassCastException e) {
        	e.printStackTrace();
        }catch(InstantiationException e) {
        	e.printStackTrace();
        }catch(IllegalAccessException e) {
        	e.printStackTrace();
        }catch(Exception e) {
        	e.printStackTrace();
        }
    }
}
abstract class Hero{
	String name;
	int blood;
	int hurt;   //装备武器后的攻击力
	int addBlood;	//防御回复血量
	double coldTime;   //冷却时间
	int waitTime=0;    //晕眩时间

	//攻击技能
	public void hit(Hero h){
		h.setBlood(h.getBlood()-hurt);
		System.out.println(this.getClass().getSimpleName().toLowerCase()+"攻击"+h.getClass().getSimpleName().toLowerCase()
			+","+ "造成伤害"+hurt+"点————"
			+ this.getClass().getSimpleName().toLowerCase()+":"+this.blood+","
			+h.getClass().getSimpleName().toLowerCase()+":"+h.blood);
	}
	//防御技能，自身回血
	public void defense(Hero h){
		setBlood(getBlood()+addBlood);
		System.out.println(this.getClass().getSimpleName().toLowerCase()+"防御回血"+addBlood+"点————"
			+ this.getClass().getSimpleName().toLowerCase()+":"+this.blood+","
			+ h.getClass().getSimpleName().toLowerCase()+":"+h.blood);
	}

	public void setBlood(int blood){
		this.blood=blood;
	}
	public int getBlood(){
		return blood;
	}
}

//武器库
interface Iweapon{
	interface Wand{
		public abstract int wand();
	}
	interface Axe{
		public abstract int axe();
	}
	interface Arrow{
		public abstract int arrow();
	}
	interface Dagger{
		public abstract int dagger();
	}
	interface Sword{
		public abstract int sword();
	}
	interface Gun{
		public abstract int gun();
	}
	interface Staff{
		public abstract int staff();
	}
	interface Claw{
		public abstract int claw();
	}
	interface Totem{
		public abstract int totem();
	}	
}

class MAGE extends Hero implements Iweapon.Wand{
	public MAGE(){
		blood=100;
		coldTime=1.0;
		waitTime=0;
		addBlood=10;
		hurt=wand();
	}
	public int wand(){
		return 15;
	}
}

class HUNTER extends Hero implements Iweapon.Arrow{
	public HUNTER(){
		blood=100;
		coldTime=1.2;
		waitTime=0;
		addBlood=10;
		hurt=arrow();
	}
	public int arrow(){
		return 20;
	}
}

class WARRIOR extends Hero implements Iweapon.Axe{
	public WARRIOR(){
		blood=100;
		coldTime=1.4;
		waitTime=0;
		addBlood=10;
		hurt=axe();
	}
	public int axe(){
		return 20;
	}
}

class ROGUE extends Hero implements Iweapon.Dagger{
	public ROGUE(){
		blood=100;
		coldTime=1.6;
		waitTime=0;
		addBlood=10;
		hurt=dagger();
	}
	public int dagger(){
		return 20;
	}
}

class KNIGHT extends Hero implements Iweapon.Sword{
	public KNIGHT(){
		blood=100;
		coldTime=1.8;
		waitTime=0;
		addBlood=10;
		hurt=sword();
	}
	public int sword(){
		return 20;
	}	
}

class HACKBUTEER extends Hero implements Iweapon.Gun{
	public HACKBUTEER(){
		blood=100;
		coldTime=2.0;
		waitTime=0;
		addBlood=10;
		hurt=gun();
	}
	public int gun(){
		return 20;
	}	
}

class PRIEST extends Hero implements Iweapon.Staff{
	public PRIEST(){
		blood=100;
		coldTime=2.2;
		waitTime=0;
		addBlood=10;
		hurt=staff();
	}
	public int staff(){
		return 20;
	}	
}

class DRUID extends Hero implements Iweapon.Claw{
	public DRUID(){
		blood=100;
		coldTime=2.4;
		waitTime=0;
		addBlood=10;
		hurt=claw();
	}
	public int claw(){
		return 20;
	}	
}

class SHAMAN extends Hero implements Iweapon.Totem{
	public SHAMAN(){
		blood=100;
		coldTime=2.6;
		waitTime=0;
		addBlood=10;
		hurt=totem();
	}
	public int totem(){
		return 20;
	}
}