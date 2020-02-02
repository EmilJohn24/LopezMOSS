package moss.gui.MultiProjectMenu;


import javax.inject.Inject;

/**
 * Controls the multi-project menu
 */
public class MultiProjectMenuPresenter {
    //RENAME: renamed from Controller to Presenter to avoid confusion while reading papers on FXML conventions
    @Inject
    private MultiProjectMenuService services;
    @Inject
    private MultiProjectMenuModel model;
}
