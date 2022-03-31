package me.timickb.jigsaw.domain;

/**
 * Represents the game result.
 * Contains information about reached score
 * and game time.
 */
public record GameResult(int score, int seconds) {
}
