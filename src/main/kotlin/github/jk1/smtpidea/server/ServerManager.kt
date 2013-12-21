package github.jk1.smtpidea.server

import github.jk1.smtpidea.config.ServerConfig
import com.intellij.notification.Notification
import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications

/**
 * @author Evgeny Naumenko
 */
public trait ServerManager<T : ServerConfig> {

    public var running: Boolean

    public var configuration: T
        get() = configuration
        public set(configuration: T) {
            this.configuration = configuration
            // restart server on configuration change
            if (running) {
                this.stopServer()
                this.startServer()
            }
        }

    fun startServer()

    fun stopServer()

    protected fun notifyFailure(message: String): Unit {
        val notification = Notification("", "Title", message, NotificationType.ERROR)
        Notifications.Bus.notify(notification)
    }

}