package cs3500.pa05.model;

/**
 * Represents a program that can save/load data.
 */
public interface Persistable {
  void save(String pathName);

  void load(String pathName);
}
