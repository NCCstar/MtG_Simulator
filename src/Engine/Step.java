package Engine;

public enum Step {
    Untap,
    Upkeep,
    Draw,
    Main1,
    BeginCombat,
    DeclareAttackers,
    DeclareBlockers,
    Damage,
    EndCombat,
    Main2,
    End,
    Cleanup;
    public Step nextStep()
    {
        switch(this)
        {
            case Untap:
                return Step.Upkeep;
            case Upkeep:
                return Step.Draw;
            case Draw:
                return Step.Main1;
            case Main1:
                return Step.BeginCombat;
            case BeginCombat:
                return Step.DeclareAttackers;
            case DeclareAttackers:
                return Step.DeclareBlockers;
            case DeclareBlockers:
                return Step.Damage;
            case Damage:
                return Step.EndCombat;
            case EndCombat:
                return Step.Main2;
            case Main2:
                return Step.End;
            case End:
                return Step.Cleanup;
            case Cleanup:
                return Step.Untap;
            default:
                throw new IllegalStateException("Literally impossible bad step.");
        }
    }
    public boolean isMain()
    {
        return this == Main1 || this == Main2;
    }
}
