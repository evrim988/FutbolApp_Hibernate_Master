package org.example.utility.events;

import org.example.entities.Ball;
import org.example.entities.Match;
import org.example.entities.Player;
import org.example.utility.AverageCalculator;
import org.example.utility.ChanceConstantCalculator;

public class ShootEvent extends Event {
	public boolean shootEvent(Ball ball, Player defender, Player goalkeeper, Match match) {
		// Şut çeken oyuncunun yetenekleri
		Integer finishing = ball.getPlayerWithBall().getPlayerTechnicalAttributes().getFinishing();
		Integer shotPower = ball.getPlayerWithBall().getPlayerTechnicalAttributes().getShotPower();
		Integer decisionMaking = ball.getPlayerWithBall().getPlayerMentalAttributes().getDecisionMaking();
		int[] shootingValues = { finishing, shotPower, decisionMaking };
		double avgShooting = AverageCalculator.calculateAverage(shootingValues);
		double chance = ChanceConstantCalculator.chanceConstant(avgShooting);
		
		// Savunma oyuncusunun yetenekleri
		Integer positioning = defender.getPlayerTechnicalAttributes().getPositioning();
		Integer composure = defender.getPlayerMentalAttributes().getComposure();
		int[] defenseValues = { positioning, composure };
		double avgDefense = AverageCalculator.calculateAverage(defenseValues);
		
		// Kaleci uzaktan sut yetenekleri
		Integer reflexes = goalkeeper.getGkAttributes().getReflexes();
		Integer diving = goalkeeper.getGkAttributes().getDiving();
		Integer gkpositioning = goalkeeper.getGkAttributes().getPositioning();
		Integer oneOnOne = goalkeeper.getGkAttributes().getOneOnOne();
		
		
		//Kaleci uzaktan sut yeteneği
		int[] distanceGKValues = { reflexes, diving, gkpositioning };
		double avgDistanceGK = AverageCalculator.calculateAverage(distanceGKValues);
		
		//kaleci 1e1 yeteneği
		int[] oneOnOneGKValues = { oneOnOne, gkpositioning, reflexes };
		double avgOneOnOneGK = AverageCalculator.calculateAverage(oneOnOneGKValues);
		
		
		//TOP 0 METREDE YANİ KALECİYLE KARSI KARSIYA
		if (ball.getPosition() == 50 || ball.getPosition() == -50) {
			if (ChanceConstantCalculator.oneOnOneOutChance(avgShooting)==0) {
				if (avgShooting*chance > avgOneOnOneGK) {
					//gol oldu
					ball.setPosition(0);
					System.out.println("Goal!" + " by " + ball.getPlayerWithBall().getPersonName() + " on one on one.");
					return true;
				}
				else {
					if(ball.getPlayerWithBall().getTeam().getId()==match.getHomeTeam().getId()) {
						ball.setPosition(-50);
						ball.setPlayerWithBall(goalkeeper);
					}
					if(ball.getPlayerWithBall().getTeam().getId()==match.getAwayTeam().getId()){
						ball.setPosition(50);
						ball.setPlayerWithBall(goalkeeper);
					}
					System.out.println("Shot saved by " + goalkeeper.getPersonName() + " on one on one.");
					return false;
				}
			}
			else {
				if(ball.getPlayerWithBall().getTeam().getId()==match.getHomeTeam().getId())
					ball.setPosition(-50);
				if(ball.getPlayerWithBall().getTeam().getId()==match.getAwayTeam().getId())
					ball.setPosition(50);
				System.out.println("Out of the box! Shot missed by " + ball.getPlayerWithBall()
				                                                           .getPersonName() + " on one on one.");
				ball.setPlayerWithBall(goalkeeper);
				return false;
			}
		}
		//TOP 0-10 METRE ARASINDA
		else if (ball.getPosition() < 50 && ball.getPosition() >= 40 || ball.getPosition() <= -40 && ball.getPosition() > -50) {
			if (avgShooting*chance > avgDefense) {
				// şut defans oyuncusunu geçti
				if (ChanceConstantCalculator.distantShootOutChance(avgShooting)==0) {
					//top kaleye dogru gitti
					if (avgShooting > avgDistanceGK) {
						//gol oldu
						ball.setPosition(0);
						System.out.println("Goal!" + " by " + ball.getPlayerWithBall().getPersonName() + ".");
						return true;
					}
					else {
						//kaleci kurtardi
						if(ball.getPlayerWithBall().getTeam().getId()==match.getHomeTeam().getId())
							ball.setPosition(-50);
						if(ball.getPlayerWithBall().getTeam().getId()==match.getAwayTeam().getId())
							ball.setPosition(50);
						ball.setPlayerWithBall(goalkeeper);
						System.out.println("Shot saved by " + goalkeeper.getPersonName() + ".");
						return false;
					}
				}
				else {
					if(ball.getPlayerWithBall().getTeam().getId()==match.getHomeTeam().getId())
						ball.setPosition(-50);
					if(ball.getPlayerWithBall().getTeam().getId()==match.getAwayTeam().getId())
						ball.setPosition(50);
					System.out.println("Out of the box! Shot missed by " + ball.getPlayerWithBall().getPersonName() + ".");
					ball.setPlayerWithBall(goalkeeper);
					
					return false;
				}
			}
			else {
				//defans topu kesti
				ball.setPlayerWithBall(defender);
				System.out.println("Ball blocked by " + defender.getPersonName() + " at position " + ball.getPosition());
				return false;
			}
		}
		//TOP 10-20 METRE ARASINDA
		else if (ball.getPosition() < 40 && ball.getPosition() >= 30 || ball.getPosition() <= -30 && ball.getPosition() > -40) {
			if (avgShooting*chance > avgDefense) {
				// şut defans oyuncusunu geçti
				if (ChanceConstantCalculator.distantShootOutChance(avgShooting)==0) {
					//top kaleye dogru gitti
					if (avgShooting*chance > avgDistanceGK) {
						//gol oldu
						ball.setPosition(0);
						System.out.println("Goal!" + " by " + ball.getPlayerWithBall().getPersonName() + " from long range.");
						return true;
					}
					else {
						//kaleci kurtardi
//                  BURAYA TAKIMA GORE SETPOSITION EKLENECEK -50 VEYA 50 SEKLINDE
						if(ball.getPlayerWithBall().getTeam().getId()==match.getHomeTeam().getId())
							ball.setPosition(-50);
						if(ball.getPlayerWithBall().getTeam().getId()==match.getAwayTeam().getId())
							ball.setPosition(50);
						ball.setPlayerWithBall(goalkeeper);
						System.out.println("Shot saved by " + goalkeeper.getPersonName() + ".");
						return false;
					}
				}
				else {
					//disari vurdu
					if(ball.getPlayerWithBall().getTeam().getId()==match.getHomeTeam().getId())
						ball.setPosition(-50);
					if(ball.getPlayerWithBall().getTeam().getId()==match.getAwayTeam().getId())
						ball.setPosition(50);
					System.out.println("Out of the box! Shot missed by " + ball.getPlayerWithBall().getPersonName() + ".");
					ball.setPlayerWithBall(goalkeeper);
					return false;
				}
			}
			else {
				//defans topu kesti
				ball.setPlayerWithBall(defender);
				System.out.println("Ball blocked by " + defender.getPersonName() + " at position " + ball.getPosition());
				return false;
			}
		}
		return false;
	}
	
	public static double calculateShotOnTargetProbability(Player shooter) {
		double probability;
		Integer finishing = shooter.getPlayerTechnicalAttributes().getFinishing();
		Integer decisionMaking = shooter.getPlayerMentalAttributes().getDecisionMaking();
		Integer overall = finishing + decisionMaking;
		if (overall >= 30) {
			// 80-100 arasında %70 - %100 arası bir şans
			probability = 0.70 + 0.25 * (overall - 30) / 20;
		}
		else if (overall >= 20) {
			// 60-80 arasında %50 - %70 arası bir şans
			probability = 0.46 + 0.25 * (overall - 20) / 20;
		}
		else {
			// 60'dan düşükse %50'den düşük bir şans
			probability = 0.30 * overall / 40;
		}
		
		return probability;
	}
	
}