package nl.roka.adventofcode.aoc.runner;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class PuzzleStorage {

  private static final Logger log = LoggerFactory.getLogger(PuzzleStorage.class);
  private static final String PUZZLE_TIMES_DB = "puzzleTimes.db";
  private static final String CREATE_TABLE_SQL =
      """
              CREATE TABLE IF NOT EXISTS solves (
                  year INTEGER NOT NULL,
                  day INTEGER NOT NULL,
                  puzzlepart INTEGER NOT NULL,
                  recordedAt DATE NOT NULL,
                  answer TEXT NOT NULL,
                  verifiedAnswer TEXT,
                  timeInNanos INTEGER NOT NULL
              )""";
  private static final String SAVE_SOLVE_SQL =
      """
        insert into solves (year, day, puzzlepart, recordedAt, answer, verifiedAnswer, timeInNanos)
        values (?, ?, ?, ?, ?, ?, ?)""";

  public static void save(PuzzleRun run) {
    var url = "jdbc:sqlite:" + PUZZLE_TIMES_DB;
    try (var conn = DriverManager.getConnection(url)) {
      log.debug("Connection to SQLite has been established.");

      createTableIfNotExist(conn);

      Date recordedAt = new Date(new java.util.Date().getTime());
      saveDay(conn, recordedAt, run.dayNumber(), 1, run.silver());
      saveDay(conn, recordedAt, run.dayNumber(), 2, run.gold());

    } catch (SQLException e) {
      log.error(e.getMessage(), e);
    }
  }

  private static void createTableIfNotExist(Connection conn) throws SQLException {
    conn.prepareStatement(CREATE_TABLE_SQL).execute();
  }

  private static void saveDay(Connection conn, Date recordedAt, int day, int part, MedalRun run)
      throws SQLException {
    if (run.runAnswer().isNotCalculated()) return;

    var stmt = conn.prepareStatement(SAVE_SOLVE_SQL);
    stmt.setInt(1, Configuration.ADVENT_YEAR);
    stmt.setInt(2, day);
    stmt.setInt(3, part);
    stmt.setDate(4, recordedAt);
    stmt.setString(5, run.answer());
    stmt.setString(6, run.verified());
    stmt.setInt(7, run.duration().getNano());

    stmt.executeUpdate();
  }
}
