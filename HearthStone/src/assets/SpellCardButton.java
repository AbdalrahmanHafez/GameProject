package assets;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.cards.Card;
import model.cards.minions.Minion;
import model.cards.spells.AOESpell;
import model.cards.spells.LeechingSpell;
import model.cards.spells.MinionTargetSpell;
import model.cards.spells.Spell;

public class SpellCardButton extends CardButton{

	private Spell card;
	
	public SpellCardButton(boolean h, boolean showOverlay, boolean clickable) {
		super(h, showOverlay, clickable);
		// TODO Auto-generated constructor stub

		setImage("resources/images/Cards/spell.png");
		this.setActionCommand("spellcast");


		this.addMouseListener(new MouseAdapter() {
			public void mouseExited(MouseEvent me) {
				 ((ImageButton) me.getSource()).setImage("resources/images/Cards/spell.png");
	         }

		      });
		
	}
	

	public boolean doesspellNeedaTarget() {
		// this method checks if a spell card need a specific starget to hit/select
		if(card instanceof MinionTargetSpell)
			return true;
		if(card instanceof LeechingSpell)
			return true;
		
		return false;
	}
	
	public void setTheCard(Spell card) {
//		TODO setCradButton
		this.card = card;
		this.setImage(super.imagePath);
		if(super.hidden) {
			this.setText("");
			
		}else {
			this.setText(card.getName());
			
		}
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("spell card trig  ac " + e.getActionCommand());
		listener.actionPerformed(e);
	}
	
	
	public Spell getCard() {
		return card;
	}

	public void setCard(Spell card) {
		this.card = card;
	}
	
	
}
