package mit.simulation.climate.model.persistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import mit.simulation.climate.dao.TupleDAO;
import mit.simulation.climate.dao.VariableDAO;
import mit.simulation.climate.model.MetaData;
import mit.simulation.climate.model.Tuple;
import mit.simulation.climate.model.Variable;

public class ServerVariable extends ServerObject<VariableDAO> implements
        Variable {


    private Integer max = null;

    public ServerVariable(VariableDAO dao) {
        super(dao);
    }

    public ServerVariable(MetaData d) {
        setMetaData(d);
    }

    @Override
    public void addValue(Tuple t) {
        TupleDAO tdao = ((ServerTuple)t).getDataObject();
        tdao.setSeq(dao.getVariableToTuples().size());
        dao.addToVariableToTuples(tdao);
    }

    @Override
    public Long getId() {
        return dao.getId();
    }

    @Override
    public MetaData getMetaData() {
        return ServerRepository.instance().get(dao.getVariableToMetaData());
    }

    @Override
    public List<Tuple> getValue() {


        //we could be a lot smarter here
        List<Tuple> result = new ArrayList<Tuple>();
        List<TupleDAO> srctuples = dao.getVariableToTuples();
        Collections.sort(srctuples, new Comparator<TupleDAO>() {

            @Override
            public int compare(TupleDAO o1, TupleDAO o2) {
                return o1.getSeq() - o2.getSeq();
            }

        });

        for (TupleDAO tdao : srctuples) {
            result.add(ServerRepository.instance().get(tdao));
        }

        max = result.size()-1;
        return result;
    }

    @Override
    public void setMetaData(MetaData md) {
        dao.setVariableToMetaData(((ServerMetaData) md).getDataObject());
    }

    @Override
    public String toString() {
        String result = getMetaData().toString();
        result+=":"+getValue();
        return result;
    }


    public boolean equals(Object o) {
        return ((o instanceof ServerVariable) && dao.equals(((ServerVariable)o).dao));
    }

}
