package pt.nfriacowboy.presib.hermes.holons

class ProductHolon(holonId: String) : PresibHolon(holonId) {

    private var executionPlan = mutableListOf<String>()
    private var loadExecutionPlan = true
    private var executedService = mutableListOf<String>()
    private var serviceInExecution = ""

    fun  currentService() = serviceInExecution

    fun isFinished() = serviceInExecution == toWarehouseService()

    fun nextService() {
        if (loadExecutionPlan) {
            loadExecutionPlan = false
            executionPlan = services().split(",").toMutableList()
        }
        if (executionPlan.isNotEmpty()) {
            serviceInExecution = executionPlan.removeFirst()
        }else {
            serviceInExecution = toWarehouseService()
        }
        executedService.add(serviceInExecution)
    }
}