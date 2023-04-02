import ResultCard from './ResultCard';

function Results(props) {

    const results = props.results;

    return (
        <>
            <div>
                {results.map((r) => {
                    return (
                        <ResultCard key={r.dateTime} results={r} />
                    )
                })}
            </div>
        </>
    )
    
  }
  
  export default Results
  