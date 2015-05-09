package common.exceptions

class ConfigKeyNotFoundException(key: String) extends Exception {
  override def getMessage = s"Can't access config-key $key. Make sure your configuration file is correct."
}
