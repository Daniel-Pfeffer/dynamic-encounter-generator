package at.jku.deq.shared

abstract class AbstractFactory<T>(
    private val postCreationHandler: (T) -> T
) {

    protected fun createInternal(objectCustomizer: T.() -> Unit, obj: T): T {
        return postCreationHandler(obj.apply(objectCustomizer))
    }
}